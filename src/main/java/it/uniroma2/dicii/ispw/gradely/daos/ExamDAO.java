package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum.E4;

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

    public Exam getExamByAppelloAndCourse(Integer appello, SubjectCourse course) {
        for(Exam e : exams){
            if(e.getAppello().equals(appello) && e.getCourse().equals(course)) {
                return e; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
}
