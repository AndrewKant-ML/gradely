package it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
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
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for(SubjectCourseEnrollment e : subjectCourseEnrollments){
            if(e.getSubjectCourse().equals(course)) {
                list.add(e); //TODO implementare exception
            }
        }
        List<SubjectCourseEnrollment> daoList = DAOFactory.getDAOFactory().getSubjectCourseEnrollmentDAO().getSubjectCourseEnrollmentsBySubjectCourse(course); //TODO implementare exception
        for(SubjectCourseEnrollment e : daoList){
            if(!list.contains(e)) {
                list.add(e); //TODO implementare exceptions
            }
        }
        return list;
    }

    public List<SubjectCourseEnrollment> getSubjectCourseEnrollmentsByStudent(Student student) {
        List<SubjectCourseEnrollment> list = new ArrayList<>();
        for(SubjectCourseEnrollment e : subjectCourseEnrollments){
            if(e.getStudent().equals(student)) {
                list.add(e); //TODO implementare exception
            }
        }
        List<SubjectCourseEnrollment> daoList = DAOFactory.getDAOFactory().getSubjectCourseEnrollmentDAO().getSubjectCourseEnrollmentsByStudent(student); //TODO implementare exception
        for(SubjectCourseEnrollment e : daoList){
            if(!list.contains(e)) {
                list.add(e); //TODO implementare exceptions
            }
        }
        return list;
    }
}