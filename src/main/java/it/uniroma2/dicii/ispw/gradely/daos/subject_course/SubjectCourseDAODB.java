package it.uniroma2.dicii.ispw.gradely.daos.subject_course;

import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum.C01;


public class SubjectCourseDAODB extends AbstractSubjectCourseDAO {

    public SubjectCourseDAODB(){ //TODO implementare costruttore vero
        subjectCourses = new ArrayList<SubjectCourse>();
        subjectCourses.add(new SubjectCourse(C01,"ISPW",Year.parse("2022"),12));
    }
    public static AbstractSubjectCourseDAO getInstance(){
        if (instance == null) {
            instance = new SubjectCourseDAODB();
        }
        return instance;
    }

    @Override
    public SubjectCourse getSubjectCourseByName(String name) {
        for(SubjectCourse s : subjectCourses){
            if(s.getName().equals(name)) {
                return s; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public void update(SubjectCourse subjectCourse) {

    }

    @Override
    public List<SubjectCourse> refresh(List<SubjectCourse> subjectCourses) {
        return null;
    }


}