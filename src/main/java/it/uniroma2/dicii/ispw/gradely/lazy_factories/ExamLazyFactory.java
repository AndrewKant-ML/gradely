package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.ExamDAO;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
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

    public Exam getExamByAppelloAndCourse(Integer appello, SubjectCourse course) {
        for(Exam e : exams){
            if(e.getAppello().equals(appello) && e.getCourse().equals(course)) {
                return e; //TODO implementare exception
            }
        }
        return ExamDAO.getInstance().getExamByAppelloAndCourse(appello, course); //TODO implementare exception
    }
}
