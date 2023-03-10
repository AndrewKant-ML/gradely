package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentDAOFS implements AbstractSubjectCourseEnrollmentDAO {

    private static SubjectCourseEnrollmentDAOFS instance;
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
        return new ArrayList<>();
    }

    @Override
    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student){
        return new ArrayList<>();
    }

    public void insert(SubjectCourseEnrollment subjectCourseEnrollment){
        // To be implemented
    }

    public void delete(SubjectCourseEnrollment subjectCourseEnrollment){
        // To be implemented
    }

    public void update(SubjectCourseEnrollment subjectCourseEnrollment){
        // To be implemented
    }
}