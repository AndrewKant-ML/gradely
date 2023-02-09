package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ObjectNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

import java.util.List;


public interface DegreeCourseDAOInterface {

    DegreeCourse getDegreeCourseByName(String name) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException;

    List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException, PropertyException, ResourceNotFoundException;

    DegreeCourse getDegreeCourseByCoordinatore(Professor professor) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException;

    List<AbstractDegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException, PropertyException, ResourceNotFoundException;
}
