package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAO {
    private static DegreeCourseDAO instance;
    private List<DegreeCourse> subjectCourses;

    private DegreeCourseDAO(){ //TODO implementare costruttore vero
        subjectCourses = new ArrayList<DegreeCourse>();
        subjectCourses.add(new DegreeCourse());
    }

    public static DegreeCourseDAO getInstance(){
        if (instance == null) {
            instance = new DegreeCourseDAO();
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
