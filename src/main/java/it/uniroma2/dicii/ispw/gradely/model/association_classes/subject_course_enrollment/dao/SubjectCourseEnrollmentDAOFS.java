package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollment;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;

public class SubjectCourseEnrollmentDAOFS extends AbstractSubjectCourseEnrollmentDAO {

    private SubjectCourseEnrollmentDAOFS(){

    }

    public static synchronized AbstractSubjectCourseEnrollmentDAO getInstance(){
        if (instance == null){
            instance = new SubjectCourseEnrollmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course){
        return null;
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student){
        return null;
    }

    @Override
    public void insert(SubjectCourseEnrollment subjectCourseEnrollment){

    }

    @Override
    public void cancel(SubjectCourseEnrollment subjectCourseEnrollment){

    }

    @Override
    public void update(SubjectCourseEnrollment subjectCourseEnrollment){

    }

    @Override
    public List<SubjectCourseEnrollment> refresh(List<SubjectCourseEnrollment> subjectCourseEnrollments){
        return null;
    }

}