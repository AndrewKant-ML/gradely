package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseLazyFactory {
    private static DegreeCourseLazyFactory instance;
    private final List<DegreeCourse> degreeCourses;

    private DegreeCourseLazyFactory() {
        degreeCourses = new ArrayList<DegreeCourse>();
    }

    public static synchronized DegreeCourseLazyFactory getInstance() {
        if (instance == null) {
            instance = new DegreeCourseLazyFactory();
        }
        return instance;
    }

    public List<AbstractDegreeCourse> getDegreeCourseByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException {
        try {
            return DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getDegreeCoursesByDegreeCourseCodeList(codes);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public DegreeCourse getDegreeCourseByName(String name) throws DAOException {
        for (DegreeCourse d : degreeCourses) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        try {
            return DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getDegreeCourseByName(name);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public List<DegreeCourse> getAllDegreeCourses() throws DAOException {
        try {
            this.degreeCourses.addAll(DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getAllDegreeCourses(this.degreeCourses));
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return this.degreeCourses;
    }
}
