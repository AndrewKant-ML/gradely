package it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

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