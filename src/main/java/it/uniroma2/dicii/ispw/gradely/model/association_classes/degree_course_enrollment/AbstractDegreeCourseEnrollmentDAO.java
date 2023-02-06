package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public abstract class AbstractDegreeCourseEnrollmentDAO implements DAOInterface<DegreeCourseEnrollment> {
    protected static AbstractDegreeCourseEnrollmentDAO instance;

    protected AbstractDegreeCourseEnrollmentDAO(){
    }


    public abstract List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) throws DAOException;

    public abstract List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) throws DAOException;

}