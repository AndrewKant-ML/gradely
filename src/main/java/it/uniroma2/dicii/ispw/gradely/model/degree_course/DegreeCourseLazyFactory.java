package it.uniroma2.dicii.ispw.gradely.model.degree_course;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DegreeCourseCodeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;

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

    public List<AbstractDegreeCourse> getDegreeCourseByDegreeCourseCodeList(List<DegreeCourseCodeEnum> codes) throws DAOException, WrongDegreeCourseCodeException {
        try {
            return DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getDegreeCoursesByDegreeCourseCodeList(codes);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public DegreeCourse getDegreeCourseByName(String name) throws DAOException, ObjectNotFoundException, WrongDegreeCourseCodeException {
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

    public DegreeCourse getDegreeCourseByCoordinatore(Professor professor) throws DAOException, ObjectNotFoundException, WrongDegreeCourseCodeException {
        for (DegreeCourse d : this.degreeCourses)
            if (d.getCoordinatore().equals(professor))
                return d;
        try {
            return DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getDegreeCourseByCoordinatore(professor);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public List<DegreeCourse> getAllDegreeCourses() throws DAOException, WrongDegreeCourseCodeException {
        try {
            this.degreeCourses.addAll(DAOFactoryAbstract.getInstance().getDegreeCourseDAO().getAllDegreeCourses(this.degreeCourses));
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return this.degreeCourses;
    }
}
