package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.*;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.*;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos.CourseAssignmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos.DegreeCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos.ExamEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos.SubjectCourseEnrollmentDAODB;

public class DAOFactoryDB extends DAOFactory{
    public AbstractCourseAssignmentDAO getCourseAssignmentDAO(){
        return CourseAssignmentDAODB.getInstance();
    }
    public AbstractDegreeCourseEnrollmentDAO getDegreeCourseEnrollmentDAO(){
        return DegreeCourseEnrollmentDAODB.getInstance();
    }
    public AbstractExamEnrollmentDAO getExamEnrollmentDAO(){
        return ExamEnrollmentDAODB.getInstance();
    }
    public AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO(){
        return SubjectCourseEnrollmentDAODB.getInstance();
    }
    public AbstractDegreeCourseDAO getDegreeCourseDAO(){
        return DegreeCourseDAODB.getInstance();
    }
    public AbstractExamDAO getExamDAO(){
        return ExamDAODB.getInstance();
    }
    public AbstractPendingEventDAO getPendingEventDAO(){
        return PendingEventDAODB.getInstance();
    }
    public AbstractProfessorDAO getProfessorDAO(){
        return ProfessorDAODB.getInstance();
    }
    public AbstractSecretaryDAO getSecretaryDAO(){
        return SecretaryDAODB.getInstance();
    }
    public AbstractStudentDAO getStudentDAO(){
        return StudentDAODB.getInstance();
    }
    public AbstractSubjectCourseDAO getSubjectCourseDAO(){
        return SubjectCourseDAODB.getInstance();
    }
    public AbstractUserDAO getUserDAO(){
        return UserDAODB.getInstance();
    }
}
