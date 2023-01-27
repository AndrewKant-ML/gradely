package it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.CourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.CourseAssignment;

import java.util.ArrayList;
import java.util.List;

public class CourseAssignmentLazyFactory {
    private static CourseAssignmentLazyFactory instance;
    private List<CourseAssignment> courseAssignments;

    private CourseAssignmentLazyFactory(){
        courseAssignments = new ArrayList<CourseAssignment>();
    }

    public static CourseAssignmentLazyFactory getInstance(){
        if (instance == null) {
            instance = new CourseAssignmentLazyFactory();
        }
        return instance;
    }

    public CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course) {
        for(CourseAssignment c : courseAssignments){
            if(c.getSubjectCourse().equals(course)) {
                return c; //TODO implementare exception
            }
        }
        return CourseAssignmentDAO.getInstance().getCourseAssignmentBySubjectCourse(course); //TODO implementare exception
    }

    public List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) {
        List<CourseAssignment> lazyList = new ArrayList<>();
        for(CourseAssignment c : courseAssignments){
            if(c.getProfessor().equals(professor)) {
                lazyList.add(c); //TODO implementare exceptions
            }
        }
        List<CourseAssignment> daoList = CourseAssignmentDAO.getInstance().getCourseAssignmentsByProfessor(professor); //TODO implementare exception
        for(CourseAssignment c : daoList){
            if(!lazyList.contains(c)) {
                lazyList.add(c); //TODO implementare exceptions
            }
        }
        return lazyList;
    }
    public List<SubjectCourse> getAssignedSubjectCoursesByProfessor(Professor professor) {
        List<SubjectCourse> lazyList = new ArrayList<>();
        for(CourseAssignment c : courseAssignments){
            if(c.getProfessor().equals(professor)) {
                lazyList.add(c.getSubjectCourse()); //TODO implementare exceptions
            }
        }
        List<CourseAssignment> daoList = CourseAssignmentDAO.getInstance().getCourseAssignmentsByProfessor(professor); //TODO implementare exception
        for(CourseAssignment c : daoList){
            if(!lazyList.contains(c.getSubjectCourse())) {
                lazyList.add(c.getSubjectCourse()); //TODO implementare exceptions
            }
        }
        return lazyList;
    }
}