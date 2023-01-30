package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentDAOFS extends AbstractSubjectCourseEnrollmentDAO {

    private SubjectCourseEnrollmentDAOFS() {
        subjectCourseEnrollments = new ArrayList<SubjectCourseEnrollment>();
        subjectCourseEnrollments.add(new SubjectCourseEnrollment());
    }

    public static AbstractSubjectCourseEnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new SubjectCourseEnrollmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course) {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for (SubjectCourseEnrollment e : subjectCourseEnrollments) {
            if (e.getSubjectCourse().equals(course)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for (SubjectCourseEnrollment e : subjectCourseEnrollments) {
            if (e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        return list;
    }

    @Override
    public void update(SubjectCourseEnrollment subjectCourseEnrollment) {

    }

    @Override
    public List<SubjectCourseEnrollment> refresh(List<SubjectCourseEnrollment> subjectCourseEnrollments) {
        return null;
    }

}