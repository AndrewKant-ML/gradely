package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.List;


public abstract class AbstractDegreeCourseDAO {
    protected static AbstractDegreeCourseDAO instance;
    protected List<DegreeCourse> subjectCourses;

    protected AbstractDegreeCourseDAO(){
    }


    public abstract DegreeCourse getDegreeCourseByName(String name);

    public abstract List<DegreeCourse> getAllDegreeCourses();

    public abstract void update(DegreeCourse degreeCourse);

    public abstract List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses);

}
