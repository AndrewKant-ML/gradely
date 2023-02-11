package it.uniroma2.dicii.ispw.gradely.dao_manager;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAODB;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamDAODB;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResultDAODB;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResultDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventDAODB;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAODB;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAODB;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAODB;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAODB;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAODB;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAOInterface;

public class DAOFactoryDB extends DAOFactoryAbstract {
    @Override
    public SubjectCourseAssignmentDAOInterface getCourseAssignmentDAO(){
        return SubjectCourseAssignmentDAODB.getInstance();
    }
    @Override
    public DegreeCourseEnrollmentDAOInterface getDegreeCourseEnrollmentDAO(){
        return DegreeCourseEnrollmentDAODB.getInstance();
    }
    @Override
    public ExamEnrollmentDAOInterface getExamEnrollmentDAO(){
        return ExamEnrollmentDAODB.getInstance();
    }
    @Override
    public AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO(){
        return SubjectCourseEnrollmentDAODB.getInstance();
    }
    @Override
    public DegreeCourseDAOInterface getDegreeCourseDAO(){
        return DegreeCourseDAODB.getInstance();
    }
    @Override
    public ExamDAOInterface getExamDAO(){
        return ExamDAODB.getInstance();
    }
    @Override
    public ExamResultDAOInterface getExamResultDAO(){
        return ExamResultDAODB.getInstance();
    }
    @Override
    public PendingEventDAOInterface getPendingEventDAO(){
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
