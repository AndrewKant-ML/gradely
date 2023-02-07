package it.uniroma2.dicii.ispw.gradely.model.degree_course.dao;

import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.AbstractDegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

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
    public List<DegreeCourse>getAllDegreeCourses(List<DegreeCourse> degreeCourses) throws DAOException {
        return null;
    }

    @Override
    public DegreeCourse getDegreeCourseByCoordinatore(Professor professor) {
        return null;
    }

    @Override
    public List<AbstractDegreeCourse> getDegreeCoursesByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException {
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

}
