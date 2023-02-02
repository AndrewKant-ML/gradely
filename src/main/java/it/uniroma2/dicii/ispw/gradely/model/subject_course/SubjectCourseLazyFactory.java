package it.uniroma2.dicii.ispw.gradely.model.subject_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseLazyFactory {
    private static SubjectCourseLazyFactory instance;
    private List<SubjectCourse> subjectCourses;

    private SubjectCourseLazyFactory(){
        subjectCourses = new ArrayList<SubjectCourse>();
    }

    public static synchronized SubjectCourseLazyFactory getInstance(){
        if (instance == null){
            instance = new SubjectCourseLazyFactory();
        }
        return instance;
    }

    public SubjectCourse getSubjectCourseByName(String name) throws DAOException {
        for(SubjectCourse s : subjectCourses){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return DAOFactoryAbstract.getInstance().getSubjectCourseDAO().getSubjectCourseByName(name);
    }
}
