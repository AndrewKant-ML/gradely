package it.uniroma2.dicii.ispw.gradely.model.subject_course.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public class SubjectCourseDAOFS extends SubjectCourseDAOAbstract {

    private SubjectCourseDAOFS(){
        super();
    }

    public static synchronized SubjectCourseDAOAbstract getInstance(){
        if (instance == null){
            instance = new SubjectCourseDAOFS();
        }
        return instance;
    }

    @Override
    public SubjectCourse getSubjectCourseByName(String name) throws DAOException {
        return null; 
    }

    @Override
    public void insert(SubjectCourse subjectCourse){

    }

    @Override
    public void cancel(SubjectCourse subjectCourse){

    }

    @Override
    public void update(SubjectCourse subjectCourse){

    }

    @Override
    public List<SubjectCourse> refresh(List<SubjectCourse> subjectCourses){
        return null;
    }


}
