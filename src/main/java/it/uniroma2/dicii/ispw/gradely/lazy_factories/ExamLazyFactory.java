package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories.CourseAssignmentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

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
            if(e.getAppello().equals(appello) && e.getCourse().equals(course) && e.getSession().equals(session)) {
                return e; //TODO implementare exception
            }
        }
        return DAOFactory.getDAOFactory().getExamDAO().getExamByAppelloCourseAndSession(appello, course, session); //TODO implementare exception
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
}
