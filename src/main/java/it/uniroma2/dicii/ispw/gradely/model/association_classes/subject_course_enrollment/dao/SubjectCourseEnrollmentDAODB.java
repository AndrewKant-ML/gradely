package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentDAODB extends AbstractSubjectCourseEnrollmentDAO {

    private SubjectCourseEnrollmentDAODB() {

    }

    public static AbstractSubjectCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new SubjectCourseEnrollmentDAODB();
        }
        return instance;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course) {
        return null;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) {
        return null;
    }

    @Override
    public void insert(SubjectCourseEnrollment subjectCourseEnrollment) {

    }

    @Override
    public void update(SubjectCourseEnrollment subjectCourseEnrollment) {

    }

    @Override
    public List<SubjectCourseEnrollment> refresh(List<SubjectCourseEnrollment> subjectCourseEnrollments) {
        return null;
    }

}