package it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAODB extends AbstractDegreeCourseEnrollmentDAO {

    private DegreeCourseEnrollmentDAODB() {
        degreeCourseEnrollments = new ArrayList<DegreeCourseEnrollment>();
        degreeCourseEnrollments.add(new DegreeCourseEnrollment());
    }

    public static AbstractDegreeCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new DegreeCourseEnrollmentDAODB();
        }
        return instance;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) {
        List<DegreeCourseEnrollment> list = new ArrayList<>();
        for (DegreeCourseEnrollment e : degreeCourseEnrollments) {
            if (e.getDegreeCourse().equals(course)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

    @Override
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) {
        List<DegreeCourseEnrollment> list = new ArrayList<>();
        for (DegreeCourseEnrollment e : degreeCourseEnrollments) {
            if (e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

    @Override
    public void update(DegreeCourseEnrollment degreeCourseEnrollment) {

    }

    @Override
    public List<DegreeCourseEnrollment> refresh(List<DegreeCourseEnrollment> degreeCourseEnrollments) {
        return null;
    }

}