package it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractSubjectCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentDAOFS extends AbstractSubjectCourseEnrollmentDAO {
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;

    private SubjectCourseEnrollmentDAOFS() { //TODO implementare costruttore vero
        subjectCourseEnrollments = new ArrayList<SubjectCourseEnrollment>();
        subjectCourseEnrollments.add(new SubjectCourseEnrollment());
    }

    public static AbstractSubjectCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new SubjectCourseEnrollmentDAOFS();
        }
        return instance;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course) {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for (SubjectCourseEnrollment e : subjectCourseEnrollments) {
            if (e.getSubjectCourse().equals(course)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for (SubjectCourseEnrollment e : subjectCourseEnrollments) {
            if (e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

}