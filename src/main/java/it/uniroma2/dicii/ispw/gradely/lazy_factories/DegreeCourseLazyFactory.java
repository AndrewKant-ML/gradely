package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.model.DegreeCourse;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseLazyFactory {
    private static DegreeCourseLazyFactory instance;
    private List<DegreeCourse> degreeCourses;

    private DegreeCourseLazyFactory(){
        degreeCourses = new ArrayList<DegreeCourse>();
    }

    public static DegreeCourseLazyFactory getInstance(){
        if (instance == null) {
            instance = new DegreeCourseLazyFactory();
        }
        return instance;
    }

    public DegreeCourse getDegreeCourseByName(String name) {
        for(DegreeCourse d : degreeCourses){
            if(d.getName().equals(name)) {
                return d; //TODO implementare exception
            }
        }
        return DAOFactory.getDAOFactory().getDegreeCourseDAO().getDegreeCourseByName(name); //TODO implementare exception
    }
}
