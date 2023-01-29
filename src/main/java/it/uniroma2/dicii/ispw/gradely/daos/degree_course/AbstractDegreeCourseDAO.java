package it.uniroma2.dicii.ispw.gradely.daos.degree_course;

import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.List;


public abstract class AbstractDegreeCourseDAO {
    protected static AbstractDegreeCourseDAO instance;
    protected List<DegreeCourse> subjectCourses;

    protected AbstractDegreeCourseDAO(){ //TODO implementare costruttore vero
    }


    public abstract DegreeCourse getDegreeCourseByName(String name);

    public abstract List<DegreeCourse> getAllDegreeCourses();

    public abstract void update(DegreeCourse degreeCourse);

    public abstract List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses);

}
