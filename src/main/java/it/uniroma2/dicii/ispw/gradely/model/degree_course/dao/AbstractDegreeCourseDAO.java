package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.AbstractDegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

import java.util.List;


public abstract class AbstractDegreeCourseDAO implements DAODBAbstract<DegreeCourse> {
    protected static AbstractDegreeCourseDAO instance;

    protected AbstractDegreeCourseDAO(){
    }


    public abstract DegreeCourse getDegreeCourseByName(String name) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException;

    public abstract List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException, PropertyException, ResourceNotFoundException;

    public abstract DegreeCourse getDegreeCourseByCoordinatore(Professor professor) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException;

    public abstract List<AbstractDegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException, PropertyException, ResourceNotFoundException;
}
