package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.List;


public abstract class AbstractDegreeCourseDAO {
    protected static AbstractDegreeCourseDAO instance;

    protected AbstractDegreeCourseDAO(){ //TODO implementare costruttore vero
    }


    public abstract DegreeCourse getDegreeCourseByName(String name);

    public abstract List<DegreeCourse> getAllDegreeCourses();

}
