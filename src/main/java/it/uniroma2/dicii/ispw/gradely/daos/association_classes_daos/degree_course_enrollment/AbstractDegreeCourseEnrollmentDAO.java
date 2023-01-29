package it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.List;

public abstract class AbstractDegreeCourseEnrollmentDAO {
    protected static AbstractDegreeCourseEnrollmentDAO instance;
    protected List<DegreeCourseEnrollment> degreeCourseEnrollments;

    protected AbstractDegreeCourseEnrollmentDAO() { //TODO implementare costruttore vero
    }


    public abstract List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course);

    public abstract List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student);

    public abstract void update(DegreeCourseEnrollment degreeCourseEnrollment);

    public abstract List<DegreeCourseEnrollment> refresh(List<DegreeCourseEnrollment> degreeCourseEnrollments);

}