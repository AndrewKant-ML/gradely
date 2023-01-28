package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentDAODB extends AbstractSubjectCourseEnrollmentDAO {
    private static SubjectCourseEnrollmentDAODB instance;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;

    private SubjectCourseEnrollmentDAODB() { //TODO implementare costruttore vero
        subjectCourseEnrollments = new ArrayList<SubjectCourseEnrollment>();
        subjectCourseEnrollments.add(new SubjectCourseEnrollment());
    }

    public static SubjectCourseEnrollmentDAODB getInstance() {
        if (instance == null) {
            instance = new SubjectCourseEnrollmentDAODB();
        }
        return instance;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course) {
        List<SubjectCourseEnrollment> lazyList = new ArrayList<>();
        for (SubjectCourseEnrollment e : subjectCourseEnrollments) {
            if (e.getSubjectCourse().equals(course)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) {
        List<SubjectCourseEnrollment> lazyList = new ArrayList<>();
        for (SubjectCourseEnrollment e : subjectCourseEnrollments) {
            if (e.getStudent().equals(student)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        return lazyList;
    }

}