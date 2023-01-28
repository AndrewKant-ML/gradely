package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractSubjectCourseDAO;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseLazyFactory {
    private static SubjectCourseLazyFactory instance;
    private List<SubjectCourse> subjectCourses;

    private SubjectCourseLazyFactory(){
        subjectCourses = new ArrayList<SubjectCourse>();
    }

    public static SubjectCourseLazyFactory getInstance(){
        if (instance == null) {
            instance = new SubjectCourseLazyFactory();
        }
        return instance;
    }

    public SubjectCourse getSubjectCourseByName(String name) {
        for(SubjectCourse s : subjectCourses){
            if(s.getName().equals(name)) {
                return s; //TODO implementare exception
            }
        }
        return AbstractSubjectCourseDAO.getInstance().getSubjectCourseByName(name); //TODO implementare exception
    }
}
