package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAOFS extends AbstractDegreeCourseEnrollmentDAO {

    private DegreeCourseEnrollmentDAOFS() {

    }

    public static AbstractDegreeCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new DegreeCourseEnrollmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) {
        return null;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) {
        return null;
    }

    @Override
    public void insert(DegreeCourseEnrollment degreeCourseEnrollment) {

    }

    @Override
    public void cancel(DegreeCourseEnrollment degreeCourseEnrollment) {

    }

    @Override
    public void update(DegreeCourseEnrollment degreeCourseEnrollment) {

    }

    @Override
    public List<DegreeCourseEnrollment> refresh(List<DegreeCourseEnrollment> degreeCourseEnrollments) {
        return null;
    }

}