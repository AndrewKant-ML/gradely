package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;

import java.util.List;

public abstract class AbstractSubjectCourseEnrollmentDAO implements DAOInterface<SubjectCourseEnrollment> {
    protected static AbstractSubjectCourseEnrollmentDAO instance;

    protected AbstractSubjectCourseEnrollmentDAO() {
    }

    public abstract List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course);

    public abstract List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student);

}