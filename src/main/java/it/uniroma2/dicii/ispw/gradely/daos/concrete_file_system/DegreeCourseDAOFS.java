package it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAOFS extends AbstractDegreeCourseDAO {
    private List<DegreeCourse> subjectCourses;

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

    public DegreeCourse getDegreeCourseByName(String name) {
        for(DegreeCourse s : subjectCourses){
            if(s.getName().equals(name)) {
                return s; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }



}
