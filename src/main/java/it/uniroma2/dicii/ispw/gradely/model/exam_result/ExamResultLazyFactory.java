package it.uniroma2.dicii.ispw.gradely.model.exam_result;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamResultLazyFactory {
    private static ExamResultLazyFactory instance;
    private List<Exam> exams;

    private ExamResultLazyFactory(){
        exams = new ArrayList<Exam>();
    }

    public static ExamResultLazyFactory getInstance(){
        if (instance == null) {
            instance = new ExamResultLazyFactory();
        }
        return instance;
    }

    public Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) {
        for(Exam e : exams){
            if(e.getAppello().equals(appello) && e.getSubjectCourse().equals(course) && e.getSession().equals(session)) {
                return e; //TODO implementare exception
            }
        }
        return DAOFactoryAbstract.getDAOFactory().getExamDAO().getExamByAppelloAndSubjectCourseAndSession(appello, course, session); //TODO implementare exception
    }

    public List<Exam> getGradableExams(Professor professor){
        List<Exam> list = new ArrayList<>();
        for(SubjectCourse c : CourseAssignmentLazyFactory.getInstance().getAssignedSubjectCoursesByProfessor(professor)) {
            for (Exam e : c.getExams()){
                if (e.getVerbalizable()==true){
                    list.add(e);
                }
            }
        }
        return list;
    }

    public void update (Exam exam){
        DAOFactoryAbstract.getDAOFactory().getExamDAO().update(exam);
    }
}
