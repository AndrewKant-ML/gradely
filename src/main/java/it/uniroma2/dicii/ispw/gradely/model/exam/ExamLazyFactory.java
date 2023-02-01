package it.uniroma2.dicii.ispw.gradely.model.exam;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamLazyFactory {
    private static ExamLazyFactory instance;
    private List<Exam> exams;

    private ExamLazyFactory(){
        exams = new ArrayList<Exam>();
    }

    public static ExamLazyFactory getInstance(){
        if (instance == null) {
            instance = new ExamLazyFactory();
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
                if (e.getGradable()){
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
