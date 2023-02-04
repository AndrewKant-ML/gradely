package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseLazyFactory {
    private static DegreeCourseLazyFactory instance;
    private List<DegreeCourse> degreeCourses;

    private DegreeCourseLazyFactory() {
        degreeCourses = new ArrayList<DegreeCourse>();
    }

    public static synchronized DegreeCourseLazyFactory getInstance() {
        if (instance == null) {
            instance = new DegreeCourseLazyFactory();
        }
        return instance;
    }

    public List<DegreeCourse> getDegreeCourseByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) {

    }

    public DegreeCourse getDegreeCourseByName(String name) throws DAOException {
        for (DegreeCourse d : degreeCourses) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getDegreeCourseByName(name);
    }

    public List<DegreeCourse> getAllDegreeCourses() throws DAOException {
        this.degreeCourses.addAll(DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getAllDegreeCourses(this.degreeCourses));
        return this.degreeCourses;
    }
}
