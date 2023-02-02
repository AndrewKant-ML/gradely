package it.uniroma2.dicii.ispw.gradely.dao_factories;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao.CourseAssignmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.dao.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.dao.DegreeCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao.ExamEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao.SubjectCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.dao.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.dao.DegreeCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.model.exam.dao.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.model.exam.dao.ExamDAODB;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.dao.AbstractExamResultDAO;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.dao.ExamResultDAODB;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.dao.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.dao.PendingEventDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.dao.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.dao.ProfessorDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.dao.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.dao.SecretaryDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.student.dao.AbstractStudentDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.student.dao.StudentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.dao.SubjectCourseDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.dao.SubjectCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.model.timer.dao.TimerDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.timer.dao.TimerDAODB;
import it.uniroma2.dicii.ispw.gradely.model.user.dao.UserDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.user.dao.UserDAODB;

public class DAOFactoryDB extends DAOFactoryAbstract {
    @Override
    public AbstractCourseAssignmentDAO getCourseAssignmentDAO(){
        return CourseAssignmentDAODB.getInstance();
    }
    @Override
    public AbstractDegreeCourseEnrollmentDAO getDegreeCourseEnrollmentDAO(){
        return DegreeCourseEnrollmentDAODB.getInstance();
    }
    @Override
    public AbstractExamEnrollmentDAO getExamEnrollmentDAO(){
        return ExamEnrollmentDAODB.getInstance();
    }
    @Override
    public AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO(){
        return SubjectCourseEnrollmentDAODB.getInstance();
    }
    @Override
    public AbstractDegreeCourseDAO getDegreeCourseDAO(){
        return DegreeCourseDAODB.getInstance();
    }
    @Override
    public AbstractExamDAO getExamDAO(){
        return ExamDAODB.getInstance();
    }
    @Override
    public AbstractExamResultDAO getExamResultDAO() {
        return ExamResultDAODB.getInstance();
    }
    @Override
    public AbstractPendingEventDAO getPendingEventDAO(){
        return PendingEventDAODB.getInstance();
    }
    @Override
    public AbstractProfessorDAO getProfessorDAO(){
        return ProfessorDAODB.getInstance();
    }
    @Override
    public AbstractSecretaryDAO getSecretaryDAO(){
        return SecretaryDAODB.getInstance();
    }
    @Override
    public AbstractStudentDAO getStudentDAO(){
        return StudentDAODB.getInstance();
    }
    @Override
    public SubjectCourseDAOAbstract getSubjectCourseDAO(){
        return SubjectCourseDAODB.getInstance();
    }
    @Override
    public TimerDAOAbstract getTimerDAO() {
        return TimerDAODB.getInstance();
    }
    @Override
    public UserDAOAbstract getUserDAO(){
        return UserDAODB.getInstance();
    }
}
