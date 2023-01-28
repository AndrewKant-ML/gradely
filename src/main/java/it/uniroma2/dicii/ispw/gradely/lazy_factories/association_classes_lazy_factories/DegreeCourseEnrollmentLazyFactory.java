package it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.DegreeCourseEnrollment;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentLazyFactory {
    private static DegreeCourseEnrollmentLazyFactory instance;
    private List<DegreeCourseEnrollment> degreeCourseEnrollments;

    private DegreeCourseEnrollmentLazyFactory(){
        degreeCourseEnrollments = new ArrayList<DegreeCourseEnrollment>();
    }

    public static DegreeCourseEnrollmentLazyFactory getInstance(){
        if (instance == null) {
            instance = new DegreeCourseEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) {
        List<DegreeCourseEnrollment> lazyList = new ArrayList<>();
        for(DegreeCourseEnrollment e : degreeCourseEnrollments){
            if(e.getDegreeCourse().equals(course)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        List<DegreeCourseEnrollment> daoList = AbstractDegreeCourseEnrollmentDAO.getInstance().getDegreeCourseEnrollmentsByDegreeCourse(course); //TODO implementare exception
        for(DegreeCourseEnrollment e : daoList){
            if(!lazyList.contains(e)) {
                lazyList.add(e); //TODO implementare exceptions
            }
        }
        return lazyList;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) {
        List<DegreeCourseEnrollment> lazyList = new ArrayList<>();
        for(DegreeCourseEnrollment e : degreeCourseEnrollments){
            if(e.getStudent().equals(student)) {
                lazyList.add(e); //TODO implementare exception
            }
        }
        List<DegreeCourseEnrollment> daoList = AbstractDegreeCourseEnrollmentDAO.getInstance().getDegreeCourseEnrollmentsByStudent(student); //TODO implementare exception
        for(DegreeCourseEnrollment e : daoList){
            if(!lazyList.contains(e)) {
                lazyList.add(e); //TODO implementare exceptions
            }
        }
        return lazyList;
    }

    public Boolean checkDegreeCourseEnrollmentPresence(Student student, DegreeCourse course){
        List<DegreeCourseEnrollment> list = getDegreeCourseEnrollmentsByStudent(student);
        for(DegreeCourseEnrollment e : list){
            if(!e.getDegreeCourse().equals(course)) {
                return true; //TODO implementare exceptions
            }
        }
        return false;
    }
}