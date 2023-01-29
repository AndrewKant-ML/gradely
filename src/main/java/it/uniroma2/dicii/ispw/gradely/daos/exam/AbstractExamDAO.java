package it.uniroma2.dicii.ispw.gradely.daos.exam;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.util.List;

public abstract class AbstractExamDAO {
    protected static AbstractExamDAO instance;
    protected List<Exam> exams;

    protected AbstractExamDAO(){ //TODO implementare costruttore vero
    }


    public abstract Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session);

    public abstract void update(Exam exam);
    public abstract List<Exam> refresh(List<Exam> exams);

}
