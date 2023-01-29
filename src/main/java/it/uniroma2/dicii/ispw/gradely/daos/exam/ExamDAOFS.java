package it.uniroma2.dicii.ispw.gradely.daos.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class ExamDAOFS extends AbstractExamDAO {

    private ExamDAOFS(){ //TODO implementare costruttore vero
        exams = new ArrayList<Exam>();
        exams.add(new Exam());
    }

    public static AbstractExamDAO getInstance(){
        if (instance == null) {
            instance = new ExamDAOFS();
        }
        return instance;
    }

    @Override
    public Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) {
        for(Exam e : exams){
            if(e.getAppello().equals(appello) && e.getCourse().equals(course) && e.getSession().equals(session)) {
                return e; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public void update(Exam exam){
        System.out.println("Updated");
    }

    @Override
    public List<Exam> refresh(List<Exam> exams) {
        return null;
    }

}
