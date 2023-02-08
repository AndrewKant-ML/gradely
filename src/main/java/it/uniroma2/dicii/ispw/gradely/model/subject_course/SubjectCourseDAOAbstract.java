package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Year;


public abstract class SubjectCourseDAOAbstract extends DAOAbstract<SubjectCourse> {
    protected static SubjectCourseDAOAbstract instance;
    protected SubjectCourseDAOAbstract(){

    }

    abstract void insert(SubjectCourse subjectCourse) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(SubjectCourse subjectCourse) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(SubjectCourse subjectCourse) throws DAOException, PropertyException, ResourceNotFoundException;

    abstract void setQueryParameters(PreparedStatement stmt, SubjectCourse subjectCourse) throws SQLException;

    public abstract SubjectCourse getSubjectCourseByNameCodeCfuAndAcademicYear(String name, SubjectCourseCodeEnum code, Integer cfu, Year academicYear) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException;

}
