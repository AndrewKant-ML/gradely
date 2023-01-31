package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;

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
        for (DegreeCourse d : degreeCourses) {
            if (d.getName().equals(name)) {
                return d; //TODO implementare exception
            }
        }
        return DAOFactoryAbstract.getDAOFactory().getDegreeCourseDAO().getDegreeCourseByName(name); //TODO implementare exception
    }

    public List<DegreeCourse> getDegreeCourses() {
        // TODO implement correct query
        this.degreeCourses = DAOFactoryAbstract.getDAOFactory().getDegreeCourseDAO().getAllDegreeCourses();
        return this.degreeCourses;
    }
}
