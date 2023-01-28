package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.*;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.*;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.association_classes_daos.CourseAssignmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.association_classes_daos.DegreeCourseEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.association_classes_daos.ExamEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system.association_classes_daos.SubjectCourseEnrollmentDAOFS;

public class DAOFactoryFS extends DAOFactory{
    public AbstractCourseAssignmentDAO getCourseAssignmentDAO(){
        return CourseAssignmentDAOFS.getInstance();
    }
    public AbstractDegreeCourseEnrollmentDAO getDegreeCourseEnrollmentDAO(){
        return DegreeCourseEnrollmentDAOFS.getInstance();
    }
    public AbstractExamEnrollmentDAO getExamEnrollmentDAO(){
        return ExamEnrollmentDAOFS.getInstance();
    }
    public AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO(){
        return SubjectCourseEnrollmentDAOFS.getInstance();
    }
    public AbstractDegreeCourseDAO getDegreeCourseDAO(){
        return DegreeCourseDAOFS.getInstance();
    }
    public AbstractExamDAO getExamDAO(){
        return ExamDAOFS.getInstance();
    }
    public AbstractPendingEventDAO getPendingEventDAO(){
        return PendingEventDAOFS.getInstance();
    }
    public AbstractProfessorDAO getProfessorDAO(){
        return ProfessorDAOFS.getInstance();
    }
    public AbstractSecretaryDAO getSecretaryDAO(){
        return SecretaryDAOFS.getInstance();
    }
    public AbstractStudentDAO getStudentDAO(){
        return StudentDAOFS.getInstance();
    }
    public AbstractSubjectCourseDAO getSubjectCourseDAO(){
        return SubjectCourseDAOFS.getInstance();
    }
    public AbstractUserDAO getUserDAO(){
        return UserDAOFS.getInstance();
    }
}
