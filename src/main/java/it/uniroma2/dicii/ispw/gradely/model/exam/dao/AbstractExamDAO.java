package it.uniroma2.dicii.ispw.gradely.model.exam.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.AppelloEnum;
import it.uniroma2.dicii.ispw.gradely.enums.SessionEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

public abstract class AbstractExamDAO implements DAOAbstract<Exam> {
    protected static AbstractExamDAO instance;

    protected AbstractExamDAO(){
    }


    public abstract Exam getExamByAppelloAndSubjectCourseAndSession(AppelloEnum appello, SubjectCourse course, SessionEnum session) throws DAOException;

}
