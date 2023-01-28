package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDegreeCourseDAO {
    private static AbstractDegreeCourseDAO instance;
    private List<DegreeCourse> subjectCourses;

    private AbstractDegreeCourseDAO(){ //TODO implementare costruttore vero
        subjectCourses = new ArrayList<DegreeCourse>();
        subjectCourses.add(new DegreeCourse());
    }

    public static AbstractDegreeCourseDAO getInstance(){
        if (instance == null) {
            instance = new AbstractDegreeCourseDAO();
        }
        return instance;
    }

    public DegreeCourse getDegreeCourseByName(String name) {
        for(DegreeCourse s : subjectCourses){
            if(s.getName().equals(name)) {
                return s; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }



}
