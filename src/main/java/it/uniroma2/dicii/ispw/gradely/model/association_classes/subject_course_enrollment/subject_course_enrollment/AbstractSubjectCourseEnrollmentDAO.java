package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;

import java.util.List;

public abstract class AbstractSubjectCourseEnrollmentDAO {
    protected static AbstractSubjectCourseEnrollmentDAO instance;
    protected List<SubjectCourseEnrollment> subjectCourseEnrollments;

    protected AbstractSubjectCourseEnrollmentDAO() {
    }

    public abstract List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course);

    public abstract List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student);

    public abstract void update(SubjectCourseEnrollment subjectCourseEnrollment);

    public abstract List<SubjectCourseEnrollment> refresh(List<SubjectCourseEnrollment> subjectCourseEnrollments);

}