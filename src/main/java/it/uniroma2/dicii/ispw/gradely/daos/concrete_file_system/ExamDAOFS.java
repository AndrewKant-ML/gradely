package it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamDAOFS extends AbstractExamDAO {
    private static ExamDAOFS instance;
    private List<Exam> exams;

    private ExamDAOFS(){ //TODO implementare costruttore vero
        exams = new ArrayList<Exam>();
        exams.add(new Exam());
    }

    public static ExamDAOFS getInstance(){
        if (instance == null) {
            instance = new ExamDAOFS();
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
