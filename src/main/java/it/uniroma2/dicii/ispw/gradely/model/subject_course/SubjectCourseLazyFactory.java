package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryDB;

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
        return DAOFactoryAbstract.getDAOFactory().getSubjectCourseDAO().getSubjectCourseByName(name); //TODO implementare exception
    }
}
