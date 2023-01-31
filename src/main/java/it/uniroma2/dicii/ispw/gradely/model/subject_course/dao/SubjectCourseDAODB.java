package it.uniroma2.dicii.ispw.gradely.model.subject_course.dao;

import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public class SubjectCourseDAODB extends SubjectCourseDAOAbstract {

    public SubjectCourseDAODB(){
        super();
    }
    public static SubjectCourseDAOAbstract getInstance(){
        if (instance == null) {
            instance = new SubjectCourseDAODB();
        }
        return instance;
    }

    @Override
    public SubjectCourse getSubjectCourseByName(String name) {
        return null;
    }

    @Override
    public void insert(SubjectCourse subjectCourse) {

    }

    @Override
    public void cancel(SubjectCourse subjectCourse) {

    }

    @Override
    public void update(SubjectCourse subjectCourse) {

    }

    @Override
    public List<SubjectCourse> refresh(List<SubjectCourse> subjectCourses) {
        return null;
    }


}
