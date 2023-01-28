package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.*;
import it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.AbstractDegreeCourse;

public abstract class DAOFactory {

    private static DAOFactory me = null;

    protected DAOFactory(){

    }

    public static synchronized DAOFactory getDAOFactory(){
        if (me == null){
            String persistenceType = System.getProperty("gradely.persistence_type");

            switch (PersistenceTypeEnum.valueOf(persistenceType)){
                case DB -> me = new DAOFactoryDB();
                case FS -> me = new DAOFactoryFS();
            }
        }
        return me;
    }

    public abstract AbstractCCDDAO createCCDDAO();
    public abstract AbstractDegreeCourse createDegreeCourse();
    public abstract AbstractExamDAO createExamDAO();
    public abstract AbstractPendingEventDAO createPendingEventDAO();
    public abstract AbstractProfessorDAO createProfessorDAO();
    public abstract AbstractSecretaryDAO createSecretaryDAO();
    public abstract AbstractStudentDAO createStudentDAO();
    public abstract AbstractSubjectCourseDAO createSubjectCourseDAO();
    public abstract AbstractUserDAO createUserDAO();

}
