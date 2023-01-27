package it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.SubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.SubjectCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseEnrollmentLazyFactory {
    private static SubjectCourseEnrollmentLazyFactory instance;
    private List<SubjectCourseEnrollment> subjectCourseEnrollments;

    private SubjectCourseEnrollmentLazyFactory(){
        subjectCourseEnrollments = new ArrayList<SubjectCourseEnrollment>();
    }

    public static SubjectCourseEnrollmentLazyFactory getInstance(){
        if (instance == null) {
            instance = new SubjectCourseEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsBySubjectCourse(SubjectCourse course) {
        List<SubjectCourseEnrollment> lazyList = new ArrayList<>();
        for(SubjectCourseEnrollment e : subjectCourseEnrollments){
            if(e.getSubjectCourse().equals(course)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        List<SubjectCourseEnrollment> DAOList = SubjectCourseEnrollmentDAO.getInstance().getSubjectCourseEnrollmentsBySubjectCourse(course); //TODO implementare exception
        for(SubjectCourseEnrollment e : DAOList){
            if(!lazyList.contains(e)) {
                lazyList.add(e); //TODO implementare exceptions
            }
        }
        return lazyList;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) {
        List<SubjectCourseEnrollment> lazyList = new ArrayList<>();
        for(SubjectCourseEnrollment e : subjectCourseEnrollments){
            if(e.getStudent().equals(student)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        List<SubjectCourseEnrollment> DAOList = SubjectCourseEnrollmentDAO.getInstance().getSubjectCourseEnrollmentsByStudent(student); //TODO implementare exception
        for(SubjectCourseEnrollment e : DAOList){
            if(!lazyList.contains(e)) {
                lazyList.add(e); //TODO implementare exceptions
            }
        }
        return lazyList;
    }
}