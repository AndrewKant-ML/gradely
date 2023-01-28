package it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubjectCourseEnrollmentDAO {
    protected static AbstractSubjectCourseEnrollmentDAO instance;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;

    protected AbstractSubjectCourseEnrollmentDAO() { //TODO implementare costruttore vero
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