package it.uniroma2.dicii.ispw.gradely.dao_manager;

import it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.dao.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.dao.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.model.exam.dao.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.dao.AbstractExamResultDAO;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.dao.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.dao.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.dao.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.student.dao.AbstractStudentDAO;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.dao.SubjectCourseDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.timer.dao.TimerDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.user.dao.UserDAOAbstract;

import static it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum.DB;
import static it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum.FS;

public abstract class DAOFactoryAbstract {

    private static DAOFactoryAbstract me = null;

    protected DAOFactoryAbstract(){

    }

    public static synchronized DAOFactoryAbstract getInstance(){
        if (me == null){
            String persistenceType = System.getProperty("gradely.persistence_type");

            if (PersistenceTypeEnum.valueOf(persistenceType) == DB){
                me = new DAOFactoryDB();

            } else if (PersistenceTypeEnum.valueOf(persistenceType) == FS){
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
    public abstract AbstractExamResultDAO getExamResultDAO();
    public abstract AbstractPendingEventDAO getPendingEventDAO();
    public abstract AbstractProfessorDAO getProfessorDAO();
    public abstract AbstractSecretaryDAO getSecretaryDAO();
    public abstract AbstractStudentDAO getStudentDAO();
    public abstract SubjectCourseDAOAbstract getSubjectCourseDAO();
    public abstract TimerDAOAbstract getTimerDAO();
    public abstract UserDAOAbstract getUserDAO();

}
