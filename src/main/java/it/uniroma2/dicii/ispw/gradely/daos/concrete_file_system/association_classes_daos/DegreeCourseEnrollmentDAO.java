package it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAO {
    private static DegreeCourseEnrollmentDAO instance;
    private List<DegreeCourseEnrollment> degreeCourseEnrollments;

    private DegreeCourseEnrollmentDAO() { //TODO implementare costruttore vero
        degreeCourseEnrollments = new ArrayList<DegreeCourseEnrollment>();
        degreeCourseEnrollments.add(new DegreeCourseEnrollment());
    }

    public static DegreeCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new DegreeCourseEnrollmentDAO();
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