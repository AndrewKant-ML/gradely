package it.uniroma2.dicii.ispw.gradely.model.subject_course.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public abstract class SubjectCourseDAOAbstract implements DAOInterface <SubjectCourse>{
    protected static SubjectCourseDAOAbstract instance;
    protected SubjectCourseDAOAbstract(){

    }

    public abstract SubjectCourse getSubjectCourseByName(String name);

}
