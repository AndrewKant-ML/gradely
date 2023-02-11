package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.WrongDegreeCourseCodeException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public interface AbstractDegreeCourseEnrollmentDAO  {

    List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) throws DAOException, PropertyException, ResourceNotFoundException;

    List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException;

}