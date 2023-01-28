package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static it.uniroma2.dicii.ispw.gradely.enums.SubjectCourseCodeEnum.C01;


public class SubjectCourseDAO {
    private static SubjectCourseDAO instance;
    private List<SubjectCourse> subjectCourses;

    private SubjectCourseDAO(){ //TODO implementare costruttore vero
        subjectCourses = new ArrayList<SubjectCourse>();
        subjectCourses.add(new SubjectCourse(C01,"ISPW",Year.parse("2022"),12));
    }

    public static SubjectCourseDAO getInstance(){
        if (instance == null) {
            instance = new SubjectCourseDAO();
        }
        return instance;
    }

    public SubjectCourse getSubjectCourseByName(String name) {
        for(SubjectCourse s : subjectCourses){
            if(s.getName().equals(name)) {
                return s; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }



}
