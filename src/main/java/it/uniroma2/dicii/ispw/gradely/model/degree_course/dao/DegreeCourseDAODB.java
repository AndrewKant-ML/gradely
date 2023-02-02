package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.enums.*;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.ArrayList;
import java.util.List;


public class DegreeCourseDAODB extends AbstractDegreeCourseDAO {

    private DegreeCourseDAODB(){ 

    }

    public static synchronized AbstractDegreeCourseDAO getInstance(){
        if (instance == null){
            instance = new DegreeCourseDAODB();
        }
        return instance;
    }

    @Override
    public DegreeCourse getDegreeCourseByName(String name) throws DAOException {
        return null;
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException {
        // TODO implement correct query
        List<DegreeCourse> courses = new ArrayList<>();
        courses.add(new DegreeCourse(DegreeCourseCodeEnum.L03, "Ingegneria informatica", FacoltaEnum.INGEGNERIA, DipartimentoEnum.DICII, DegreeCourseTypeEnum.TRIENNALE, TestTypeEnum.MUR, new ArrayList<>()));
        return courses;
    }

    @Override
    public void insert(DegreeCourse degreeCourse){

    }

    @Override
    public void cancel(DegreeCourse degreeCourse){

    }

    @Override
    public void update(DegreeCourse degreeCourse){

    }

    @Override
    public List<DegreeCourse> refresh(List<DegreeCourse> degreeCourses){
        return null;
    }
}
