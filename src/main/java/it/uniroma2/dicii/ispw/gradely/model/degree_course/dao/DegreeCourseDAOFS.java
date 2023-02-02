package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;

import java.util.List;


public class DegreeCourseDAOFS extends AbstractDegreeCourseDAO {

    private DegreeCourseDAOFS(){ 

    }

    public static synchronized AbstractDegreeCourseDAO getInstance(){
        if (instance == null){
            instance = new DegreeCourseDAOFS();
        }
        return instance;
    }

    @Override
    public DegreeCourse getDegreeCourseByName(String name) throws DAOException {
        return null; 
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses() throws DAOException {
        return null;
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
