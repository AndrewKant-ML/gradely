package it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDegreeCourseEnrollmentDAO {
    private static AbstractDegreeCourseEnrollmentDAO instance;
    private List<DegreeCourseEnrollment> degreeCourseEnrollments;

    private AbstractDegreeCourseEnrollmentDAO() { //TODO implementare costruttore vero
        degreeCourseEnrollments = new ArrayList<DegreeCourseEnrollment>();
        degreeCourseEnrollments.add(new DegreeCourseEnrollment());
    }

    public static AbstractDegreeCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new AbstractDegreeCourseEnrollmentDAO();
        }
        return instance;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) {
        List<DegreeCourseEnrollment> lazyList = new ArrayList<>();
        for (DegreeCourseEnrollment e : degreeCourseEnrollments) {
            if (e.getDegreeCourse().equals(course)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) {
        List<DegreeCourseEnrollment> lazyList = new ArrayList<>();
        for (DegreeCourseEnrollment e : degreeCourseEnrollments) {
            if (e.getStudent().equals(student)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

}