package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamDAO {
    private static ExamDAO instance;
    private List<Exam> exams;

    private ExamDAO(){ //TODO implementare costruttore vero
        exams = new ArrayList<Exam>();
        exams.add(new Exam());
    }

    public static ExamDAO getInstance(){
        if (instance == null) {
            instance = new ExamDAO();
        }
        return instance;
    }

    public Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) {
        for(Exam e : exams){
            if(e.getAppello().equals(appello) && e.getCourse().equals(course) && e.getSession().equals(session)) {
                return e; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }

}
