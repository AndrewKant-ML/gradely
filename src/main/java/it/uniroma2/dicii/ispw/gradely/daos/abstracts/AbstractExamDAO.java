package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.Exam;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.ExamEnrollment;

public abstract class AbstractExamDAO {
    protected static AbstractExamDAO instance;

    protected AbstractExamDAO(){ //TODO implementare costruttore vero
    }


    public abstract Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session);

    public abstract void update(Exam exam);
}
