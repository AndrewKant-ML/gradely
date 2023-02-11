package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

import java.util.List;


public interface DegreeCourseDAOInterface {

    DegreeCourse getDegreeCourseByName(String name) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException;

    List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException;

    DegreeCourse getDegreeCourseByCoordinatore(Professor professor) throws DAOException, ObjectNotFoundException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException;

    List<AbstractDegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException;
}
