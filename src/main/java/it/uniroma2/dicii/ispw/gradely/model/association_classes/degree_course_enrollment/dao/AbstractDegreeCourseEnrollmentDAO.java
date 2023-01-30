package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollment;

import java.util.List;

public abstract class AbstractDegreeCourseEnrollmentDAO {
    protected static AbstractDegreeCourseEnrollmentDAO instance;
    protected List<DegreeCourseEnrollment> degreeCourseEnrollments;

    protected AbstractDegreeCourseEnrollmentDAO() {
    }


    public abstract List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course);

    public abstract List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student);

    public abstract void update(DegreeCourseEnrollment degreeCourseEnrollment);

    public abstract List<DegreeCourseEnrollment> refresh(List<DegreeCourseEnrollment> degreeCourseEnrollments);

}