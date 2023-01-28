package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentDAODB extends AbstractDegreeCourseEnrollmentDAO {
    private static DegreeCourseEnrollmentDAODB instance;
    private List<DegreeCourseEnrollment> degreeCourseEnrollments;

    private DegreeCourseEnrollmentDAODB() { //TODO implementare costruttore vero
        degreeCourseEnrollments = new ArrayList<DegreeCourseEnrollment>();
        degreeCourseEnrollments.add(new DegreeCourseEnrollment());
    }

    public static DegreeCourseEnrollmentDAODB getInstance() {
        if (instance == null) {
            instance = new DegreeCourseEnrollmentDAODB();
        }
        return instance;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) {
        List<DegreeCourseEnrollment> list = new ArrayList<>();
        for (DegreeCourseEnrollment e : degreeCourseEnrollments) {
            if (e.getDegreeCourse().equals(course)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) {
        List<DegreeCourseEnrollment> list = new ArrayList<>();
        for (DegreeCourseEnrollment e : degreeCourseEnrollments) {
            if (e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

}