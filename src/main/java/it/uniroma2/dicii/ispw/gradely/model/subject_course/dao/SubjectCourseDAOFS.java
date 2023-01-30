package it.uniroma2.dicii.ispw.gradely.model.subject_course.dao;

import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum.C01;


public class SubjectCourseDAOFS extends SubjectCourseDAOAbstract {

    private SubjectCourseDAOFS(){
        super();
    }

    public static SubjectCourseDAOAbstract getInstance(){
        if (instance == null) {
            instance = new SubjectCourseDAOFS();
        }
        return instance;
    }

    @Override
    public SubjectCourse getSubjectCourseByName(String name) {
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(SubjectCourse subjectCourse) {

    }

    @Override
    public void update(SubjectCourse subjectCourse) {

    }

    @Override
    public List<SubjectCourse> refresh(List<SubjectCourse> subjectCourses) {
        return null;
    }


}
