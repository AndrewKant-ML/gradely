package it.uniroma2.dicii.ispw.gradely.model.subject_course.dao;

import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public abstract class AbstractSubjectCourseDAO {
    protected static AbstractSubjectCourseDAO instance;
    protected List<SubjectCourse> subjectCourses;

    protected AbstractSubjectCourseDAO(){

    }

    public abstract SubjectCourse getSubjectCourseByName(String name);
    public abstract void update(SubjectCourse subjectCourse);

    public abstract List<SubjectCourse> refresh(List<SubjectCourse> subjectCourses);

}
