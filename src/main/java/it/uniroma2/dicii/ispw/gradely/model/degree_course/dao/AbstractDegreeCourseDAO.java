package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.List;


public abstract class AbstractDegreeCourseDAO implements DAOInterface<DegreeCourse> {
    protected static AbstractDegreeCourseDAO instance;

    protected AbstractDegreeCourseDAO(){
    }


    public abstract DegreeCourse getDegreeCourseByName(String name) throws DAOException;

    public abstract List<DegreeCourse> getAllDegreeCourses() throws DAOException ;

}
