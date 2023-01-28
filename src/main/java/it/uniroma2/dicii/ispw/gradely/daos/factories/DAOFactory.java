package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.*;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum;

import static it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum.*;

public abstract class DAOFactory {

    private static DAOFactory me = null;

    protected DAOFactory(){

    }

    public static synchronized DAOFactory getDAOFactory(){
        if (me == null){
            String persistenceType = System.getProperty("gradely.persistence_type");

            if (PersistenceTypeEnum.valueOf(persistenceType) == DB){
                me = new DAOFactoryDB();

            } else if (PersistenceTypeEnum.valueOf(persistenceType) == FS) {
                me = new DAOFactoryFS();
            }
        }
        return me;
    }

    public abstract AbstractCourseAssignmentDAO getCourseAssignmentDAO();
    public abstract AbstractDegreeCourseEnrollmentDAO getDegreeCourseEnrollmentDAO();
    public abstract AbstractExamEnrollmentDAO getExamEnrollmentDAO();
    public abstract AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO();
    public abstract AbstractDegreeCourseDAO getDegreeCourseDAO();
    public abstract AbstractExamDAO getExamDAO();
    public abstract AbstractPendingEventDAO getPendingEventDAO();
    public abstract AbstractProfessorDAO getProfessorDAO();
    public abstract AbstractSecretaryDAO getSecretaryDAO();
    public abstract AbstractStudentDAO getStudentDAO();
    public abstract AbstractSubjectCourseDAO getSubjectCourseDAO();
    public abstract AbstractUserDAO getUserDAO();

}
