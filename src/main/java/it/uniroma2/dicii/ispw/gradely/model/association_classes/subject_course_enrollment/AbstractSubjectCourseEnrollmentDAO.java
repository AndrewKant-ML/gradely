package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;

public interface AbstractSubjectCourseEnrollmentDAO  {

    public abstract List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course);

    public abstract List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student);

}