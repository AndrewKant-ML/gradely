package it.uniroma2.dicii.ispw.gradely.model.exam.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;

public abstract class AbstractExamDAO implements DAOInterface<Exam> {
    protected static AbstractExamDAO instance;

    protected AbstractExamDAO(){
    }


    public abstract Exam getExamByAppelloCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session);

}
