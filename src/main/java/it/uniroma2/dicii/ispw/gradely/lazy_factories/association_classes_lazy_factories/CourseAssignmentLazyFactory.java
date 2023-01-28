package it.uniroma2.dicii.ispw.gradely.lazy_factories.association_classes_lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractCourseAssignmentDAO;
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
        return AbstractCourseAssignmentDAO.getInstance().getCourseAssignmentBySubjectCourse(course); //TODO implementare exception
    }

    public List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) {
        List<CourseAssignment> list = new ArrayList<>();
        for(CourseAssignment c : courseAssignments){
            if(c.getProfessor().equals(professor)) {
                list.add(c); //TODO implementare exceptions
            }
        }
        List<CourseAssignment> daoList = AbstractCourseAssignmentDAO.getInstance().getCourseAssignmentsByProfessor(professor); //TODO implementare exception
        for(CourseAssignment c : daoList){
            if(!list.contains(c)) {
                list.add(c); //TODO implementare exceptions
            }
        }
        return list;
    }
    public List<SubjectCourse> getAssignedSubjectCoursesByProfessor(Professor professor) {
        List<SubjectCourse> list = new ArrayList<>();
        for(CourseAssignment c : courseAssignments){
            if(c.getProfessor().equals(professor)) {
                list.add(c.getSubjectCourse()); //TODO implementare exceptions
            }
        }
        List<CourseAssignment> daoList = AbstractCourseAssignmentDAO.getInstance().getCourseAssignmentsByProfessor(professor); //TODO implementare exception
        for(CourseAssignment c : daoList){
            if(!list.contains(c.getSubjectCourse())) {
                list.add(c.getSubjectCourse()); //TODO implementare exceptions
            }
        }
        return list;
    }
}