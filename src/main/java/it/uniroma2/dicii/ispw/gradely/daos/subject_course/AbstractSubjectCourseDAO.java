package it.uniroma2.dicii.ispw.gradely.daos.subject_course;

import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

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
