package it.uniroma2.dicii.ispw.gradely.dao_manager;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.dao.ExamEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.dao.AbstractSubjectCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.dao.SubjectCourseAssignmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.dao.SubjectCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAODB;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.dao.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.dao.DegreeCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.model.exam.dao.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.model.exam.dao.ExamDAODB;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.dao.AbstractExamResultDAO;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.dao.ExamResultDAODB;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.dao.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.dao.PendingEventDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAODB;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAODB;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAODB;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAODB;

public class DAOFactoryDB extends DAOFactoryAbstract {
    @Override
    public AbstractSubjectCourseAssignmentDAO getCourseAssignmentDAO(){
        return SubjectCourseAssignmentDAODB.getInstance();
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
    public AbstractExamResultDAO getExamResultDAO(){
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
    public StudentDAOInterface getStudentDAO(){
        return StudentDAODB.getInstance();
    }
    @Override
    public SubjectCourseDAOInterface getSubjectCourseDAO(){
        return SubjectCourseDAODB.getInstance();
    }
    @Override
    public TimerDAOInterface getTimerDAO(){
        return TimerDAODB.getInstance();
    }
    @Override
    public TitleDAOInterface getTitleDAO() {
        return TitleDAODB.getInstance();
    }

    @Override
    public UserDAOInterface getUserDAO(){
        return UserDAODB.getInstance();
    }

    @Override
    public TestDAOAbstract getTestDAO() {
        return TestDAODB.getInstance();
    }

    @Override
    public TestReservationDAOAbstract getTestReservationDAO() {
        return TestReservationDAODB.getInstance();
    }
}
