package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAOFS extends AbstractDegreeCourseDAO {

    private DegreeCourseDAOFS(){ //TODO implementare costruttore vero

    }

    public static AbstractDegreeCourseDAO getInstance(){
        if (instance == null) {
            instance = new DegreeCourseDAOFS();
        }
        return instance;
    }

    @Override
    public DegreeCourse getDegreeCourseByName(String name) {
        return null; //TODO implementare exceptions
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses() {
        return null;
    }

    @Override
    public void insert(DegreeCourse degreeCourse) {

    }

    @Override
    public void cancel(DegreeCourse degreeCourse) {

    }

    @Override
    public void update(DegreeCourse degreeCourse) {

    }

    @Override
    public List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses) {
        return null;
    }
}
