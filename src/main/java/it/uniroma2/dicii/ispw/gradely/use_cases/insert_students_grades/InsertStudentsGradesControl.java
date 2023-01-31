package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.beans_general.*;
import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExamResultConfirmationEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResult;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerObserver;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsertStudentsGradesControl implements TimerObserver {

    public InsertStudentsGradesControl() {

    }

    /**
     *
     * @param token
     * @return
     * @throws MissingAuthorizationException
     */
    public ExamListBean getGradableExams(Token token) throws MissingAuthorizationException{ //TODO exceptions
        if(SessionManager.getInstance().getSessionUserByToken(token).getRole().professor()!=null){
            return new ExamListBean(createExamBeanList(ExamLazyFactory.getInstance().getGradableExams(SessionManager.getInstance().getSessionUserByToken(token).getRole().professor())));
        }
        else throw new MissingAuthorizationException("You don't have the authorization to execute the requested action, please get in touch with system administrator", new Throwable());

    }

    private List<ExamBean> createExamBeanList(List<Exam> inList){
        List<ExamBean> outList = new ArrayList<>();
        for (Exam e : inList){
            outList.add(new ExamBean(new SubjectCourseBean(e.getSubjectCourse().getCode(),e.getSubjectCourse().getName(),e.getSubjectCourse().getAcademicYear()),e.getAppello(),e.getSession()));
        }
        return outList;
    }

    public ExamEnrollmentListBean getExamEnrollments(Token token, ExamBean bean){
        List<ExamEnrollmentBean> list = new ArrayList<>();
        try{SessionManager.getInstance().getSessionUserByToken(token).getRole().professor();
        }catch (MissingAuthorizationException e){

        }
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

    public void saveExamResults(Token token, StudentGradeListBean list){
        try{
            Professor professor = SessionManager.getInstance().getSessionUserByToken(token).getRole().professor();
            List<Professor> professors = new ArrayList<>();
            for (CourseAssignment c : list.getExam().getSubjectCourse().getCourseAssignments()){
                professors.add(c.getProfessor());
            }
            if(!professors.contains(professor)) throw new MissingAuthorizationException("You don't have the authorization to execute the requested action, please get in touch with system administrator", new Throwable());
        }catch (MissingAuthorizationException e){

        }
        for (StudentGradeBean g : list.getGrades()){
            saveExamResult(g);
            PendingEventLazyFactory.getInstance().createNewPendingEventSingle(g.getEnrollmentBean().getStudent().getUser(), PendingEventTypeEnum.EVENT_1, g.getEnrollmentBean().getExam()); //TODO implementare messaggi automatici
        }
        TimerLazyFactory.getInstance().newExamConfirmationTimer(LocalDate.now().plusDays(7L), list.getExam());
    }
    public void saveExamResult(StudentGradeBean bean){
        ExamEnrollmentLazyFactory.getInstance().saveExamResult(ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentByExamAndStudent(bean.getEnrollmentBean().getExam(), bean.getEnrollmentBean().getStudent()), new ExamResult(bean.getExamResultBean().getGrade(),bean.getExamResultBean().getResult(), ExamResultConfirmationEnum.NULL));
    }

    public void acceptOrRejectExamGrade(Token token, ExamEnrollment enrollment, ExamResultConfirmationEnum decision){
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().student();
            enrollment.getExamResult().setConfirmed(decision);
        }catch (MissingAuthorizationException e){

        }
    }

    public void confirmExamVerbaleProtocolization(Token token, ProtocolBean bean) throws RuntimeException{
        try{
            SessionManager.getInstance().getSessionUserByToken(token).getRole().secretary();
            Exam e = ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getExamBean().getAppello(), SubjectCourseLazyFactory.getInstance().getSubjectCourseByName(bean.getExamBean().getCourse().getName()), bean.getExamBean().getSessione());
            e.setVerbalizable(false);
            e.setVerbaleDate(bean.getVerbaleDate());
            e.setVerbaleNumber(bean.getVerbaleNumber());
            ExamLazyFactory.getInstance().update(e);
            notifyExamProtocolization(e);
        }catch (MissingAuthorizationException e){

        }
    }
    public void notifyExamProtocolization(Exam exam){
        List<User> users = new ArrayList<>();
        for (ExamEnrollment e : exam.getEnrollments()){
            users.add(e.getStudent().getUser());
        }
        for (CourseAssignment c : exam.getSubjectCourse().getCourseAssignments()){
            users.add(c.getProfessor().getUser());
        }
        PendingEventLazyFactory.getInstance().createNewPendingEventGroup(users,PendingEventTypeEnum.EVENT_4,exam);
    }

    @Override
    public void timeIsUp(AbstractTimer timer){
        for (ExamEnrollment e : timer.examConfirmationTimer().getExam().getEnrollments()){
            if (e.getExamResult().getConfirmed()==ExamResultConfirmationEnum.NULL){
                e.getExamResult().setConfirmed(ExamResultConfirmationEnum.ACCEPTED);
                PendingEventLazyFactory.getInstance().createNewPendingEventSingle(e.getStudent().getUser(), PendingEventTypeEnum.EVENT_2, e);
            }

        }
        List<User> list = new ArrayList<>();
        for (DegreeCourse d : timer.examConfirmationTimer().getExam().getSubjectCourse().getDegreeCourses()){
            for (Secretary s : DAOFactoryAbstract.getDAOFactory().getSecretaryDAO().getSecretariesByDipartimento(d.getDipartimento())){
                list.add(s.getUser());
            }
        }
        PendingEventLazyFactory.getInstance().createNewPendingEventGroup(list,PendingEventTypeEnum.EVENT_3, timer.examConfirmationTimer().getExam());
    }
}

