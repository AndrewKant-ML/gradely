package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.general_beans.*;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.PendingEventLazyFactory;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.SubjectCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class InsertStudentsGradesControl {
    List<ExamConfirmationTimer> timers;

    public InsertStudentsGradesControl() {
        this.timers = new ArrayList<>(); //TODO timer retrival at startup
    }

    public ExamListBean getGradableExams(Token token){ //TODO exceptions
        return new ExamListBean(createExamBeanList(ExamLazyFactory.getInstance().getGradableExams(SessionManager.getInstance().getSessionUserByToken(token).getRole().professor())));
    }

    private List<ExamBean> createExamBeanList(List<Exam> inList){
        List<ExamBean> outList = new ArrayList<>();
        for (Exam e : inList){
            outList.add(new ExamBean(new SubjectCourseBean(e.getCourse().getCode(),e.getCourse().getName(),e.getCourse().getAcademicYear()),e.getAppello(),e.getSession()));
        }
        return outList;
    }

    public ExamEnrollmentListBean getExamEnrollments(ExamBean bean){
        List<ExamEnrollmentBean> list = new ArrayList<>();
        for (ExamEnrollment e : ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByExam(getExamByBean(bean))){
            list.add(new ExamEnrollmentBean(e.getStudent(),e.getExam()));
        }
        return new ExamEnrollmentListBean(list);
    }

    private Exam getExamByBean(ExamBean bean){
        return ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getAppello(), getSubjectCourseByBean(bean.getCourse()),bean.getSessione());
    }

    private SubjectCourse getSubjectCourseByBean(SubjectCourseBean bean){
        return SubjectCourseLazyFactory.getInstance().getSubjectCourseByName(bean.getName());

    }

    public void saveExamResults(StudentGradeListBean list){
        for (StudentGradeBean g : list.getGrades()){
            saveExamResult(g);
            PendingEventLazyFactory.getInstance().createNewPendingEventSingle(g.getEnrollmentBean().getStudent().getUser(), PendingEventTypeEnum.E1, "pending grade notification, system is waiting confirmation from student"); //TODO implementare messaggi automatici
        }
        timers.add(new ExamConfirmationTimer(list.getExam(), LocalDate.now().plusDays(7L)));
    }
    public void saveExamResult(StudentGradeBean bean){
        ExamEnrollmentLazyFactory.getInstance().saveExamResult(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentByExamAndStudent(bean.getEnrollmentBean().getExam(), bean.getEnrollmentBean().getStudent()), new ExamResult(bean.getExamResultBean().getGrade(),bean.getExamResultBean().getResult(), ExamResultConfirmationEnum.NULL));
    }

    public void acceptOrRejectExamGrade(ExamEnrollment enrollment, ExamResultConfirmationEnum decision){
        enrollment.getExamResult().setConfirmed(decision);
    }

    private void checkTimers(ExamConfirmationTimer timer){ //TODO timered trigger
        for (ExamConfirmationTimer t : timers){
            if (t.getConfirmationExpiration().isAfter(LocalDate.now())){
                for (ExamEnrollment e : t.getExam().getEnrollments()){
                    e.getExamResult().setConfirmed(ExamResultConfirmationEnum.ACCEPTED);
                    PendingEventLazyFactory.getInstance().createNewPendingEventSingle(e.getStudent().getUser(), PendingEventTypeEnum.E2, "The exam was marked accepted by the system due to lack of confirmation by the student");
                }
                List<User> list = new ArrayList<>();
                for (DegreeCourse d : t.getExam().getCourse().getDegreeCourses()){
                    for (Secretary s : DAOFactory.getDAOFactory().getSecretaryDAO().getSecretariesByDipartimento(d.getDipartimento())){
                        list.add(s.getUser());
                    }
                }
                PendingEventLazyFactory.getInstance().createNewPendingEventGroup(list,PendingEventTypeEnum.E3,"Please verify validity for the following exam grades");

                timers.remove(t);
            }
        }
    }

}

