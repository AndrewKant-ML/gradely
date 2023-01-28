package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades;

import it.uniroma2.dicii.ispw.gradely.general_beans.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.ExamLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

import java.util.List;

public class InsertStudentsGradesControl {
    public ExamListBean getGradableExams(Token token){ //TODO exceptions
        return new ExamListBean(createExamBeanList(ExamLazyFactory.getInstance().getGradableExams(SessionManager.getInstance().getLazySessionUser(token).getRole().Professor())));
    }

    private List<ExamBean> createExamBeanList(List<Exam> inList){
        List<ExamBean> outList = new ArrayList<>();
        for (Exam e : inList){
            outList.add(new ExamBean(new SubjectCourseBean(e.getCourse().getCode(),e.getCourse().getName(),e.getCourse().getAcademicYear()),e.getAppello(),e.getSession()));
        }
        return outList;
    }

    public ExamEnrollmentListBean getExamEnrollments(ExamBean bean){
        List<ExamEnrollment> list = new ArrayList<>();
        for (ExamEnrollment e : ExamEnrollmentLazyFactory.getInstance().getExamEnrollmentsByExam(getExamByBean(bean))){
            list.add(e.);
        }
    }

    private Exam getExamByBean(ExamBean bean){
        return ExamLazyFactory.getInstance().getExamByAppelloCourseAndSession(bean.getAppello(), getSubjectCourseByBean(bean.getCourse()),bean.getSessione());
    }

    private SubjectCourse getSubjectCourseByBean(SubjectCourseBean bean){
        return SubjectCourseLazyFactory.getInstance().getSubjectCourseByName(bean.getName());
    }
}
