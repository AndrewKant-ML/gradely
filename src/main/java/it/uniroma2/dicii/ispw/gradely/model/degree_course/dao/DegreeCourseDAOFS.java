package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAOFS extends AbstractDegreeCourseDAO {

    private DegreeCourseDAOFS(){ //TODO implementare costruttore vero
        subjectCourses = new ArrayList<DegreeCourse>();
        subjectCourses.add(new DegreeCourse());
    }

    public static AbstractDegreeCourseDAO getInstance(){
        if (instance == null) {
            instance = new DegreeCourseDAOFS();
        }
        return instance;
    }

    @Override
    public DegreeCourse getDegreeCourseByName(String name) {
        for(DegreeCourse s : subjectCourses){
            if(s.getName().equals(name)) {
                return s; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses() {
        return null;
    }

    @Override
    public void update(DegreeCourse degreeCourse) {

    }

    @Override
    public List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses) {
        return null;
    }
}
