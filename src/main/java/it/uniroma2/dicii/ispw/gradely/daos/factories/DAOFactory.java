package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.course_assignment.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.exam_enrollment.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.subject_course_enrollment.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.degree_course.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.exam.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.daos.pending_event.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.daos.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.daos.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.daos.student.AbstractStudentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.subject_course.AbstractSubjectCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.user.AbstractUserDAO;
import it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum;

import static it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum.DB;
import static it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum.FS;

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
