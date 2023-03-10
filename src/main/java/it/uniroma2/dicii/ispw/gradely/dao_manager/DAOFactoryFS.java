package it.uniroma2.dicii.ispw.gradely.dao_manager;

import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.SubjectCourseEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResultDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResultDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.ProfessorDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.SecretaryDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAOFS;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAOInterface;

public class DAOFactoryFS extends DAOFactoryAbstract {
    @Override
    public SubjectCourseAssignmentDAOInterface getCourseAssignmentDAO(){
        return SubjectCourseAssignmentDAOFS.getInstance();
    }
    @Override
    public DegreeCourseEnrollmentDAOInterface getDegreeCourseEnrollmentDAO(){
        return DegreeCourseEnrollmentDAOFS.getInstance();
    }
    @Override
    public ExamEnrollmentDAOInterface getExamEnrollmentDAO(){
        return ExamEnrollmentDAOFS.getInstance();
    }
    @Override
    public AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO(){
        return SubjectCourseEnrollmentDAOFS.getInstance();
    }
    @Override
    public DegreeCourseDAOInterface getDegreeCourseDAO(){
        return DegreeCourseDAOFS.getInstance();
    }
    @Override
    public ExamDAOInterface getExamDAO(){
        return ExamDAOFS.getInstance();
    }
    @Override
    public ExamResultDAOInterface getExamResultDAO(){
        return ExamResultDAOFS.getInstance();
    }
    @Override
    public PendingEventDAOInterface getPendingEventDAO(){
        return PendingEventDAOFS.getInstance();
    }
    @Override
    public AbstractProfessorDAO getProfessorDAO(){
        return ProfessorDAOFS.getInstance();
    }
    @Override
    public AbstractSecretaryDAO getSecretaryDAO(){
        return SecretaryDAOFS.getInstance();
    }
    @Override
    public StudentDAOInterface getStudentDAO(){
        return StudentDAOFS.getInstance();
    }
    @Override
    public SubjectCourseDAOInterface getSubjectCourseDAO(){
        return SubjectCourseDAOFS.getInstance();
    }
    @Override
    public TimerDAOInterface getTimerDAO(){
        return TimerDAOFS.getInstance();
    }

    @Override
    public TitleDAOInterface getTitleDAO() {
        return TitleDAOFS.getInstance();
    }

    @Override
    public UserDAOInterface getUserDAO() {
        return UserDAOFS.getInstance();
    }

    @Override
    public TestDAOAbstract getTestDAO() {
        return TestDAOFS.getInstance();
    }

    @Override
    public TestReservationDAOAbstract getTestReservationDAO() {
        return TestReservationDAOFS.getInstance();
    }

    @Override
    public SubjectCourseAssignmentDAOInterface getSubjectCourseAssignmentDAO() {
        return SubjectCourseAssignmentDAOFS.getInstance();
    }
}
