package it.uniroma2.dicii.ispw.gradely.dao_manager;

import it.uniroma2.dicii.ispw.gradely.PropertiesHandler;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PersistenceTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment.DegreeCourseEnrollmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.exam_enrollment.ExamEnrollmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignmentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_enrollment.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.TestReservationDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam.ExamDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.exam_result.ExamResultDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourseDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.test.TestDAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.timer.TimerDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.title.TitleDAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.user.UserDAOInterface;

public abstract class DAOFactoryAbstract {

    private static DAOFactoryAbstract me = null;

    protected DAOFactoryAbstract() {

    }

    public static synchronized DAOFactoryAbstract getInstance() throws ResourceNotFoundException, PropertyException {
        if (me == null) {
            PersistenceTypeEnum persistenceType = PersistenceTypeEnum.getPersistenceTypeByValue(PropertiesHandler.getInstance().getProperty("persistence_type"));
            if (persistenceType != null)
                switch (persistenceType) {
                    case DB -> me = new DAOFactoryDB();
                    case FS -> me = new DAOFactoryFS();
                    default -> throw new PropertyException(ExceptionMessagesEnum.UNEXPECTED_PROPERTY_NAME.message);
                }
            else
                throw new ResourceNotFoundException(ExceptionMessagesEnum.RESOURCE_NOT_FOUND.message);
        }
        return me;
    }

    public abstract SubjectCourseAssignmentDAOInterface getCourseAssignmentDAO();

    public abstract DegreeCourseEnrollmentDAOInterface getDegreeCourseEnrollmentDAO();

    public abstract ExamEnrollmentDAOInterface getExamEnrollmentDAO();

    public abstract AbstractSubjectCourseEnrollmentDAO getSubjectCourseEnrollmentDAO();
    public abstract DegreeCourseDAOInterface getDegreeCourseDAO();
    public abstract ExamDAOInterface getExamDAO();
    public abstract ExamResultDAOInterface getExamResultDAO();
    public abstract PendingEventDAOInterface getPendingEventDAO();
    public abstract AbstractProfessorDAO getProfessorDAO();
    public abstract AbstractSecretaryDAO getSecretaryDAO();
    public abstract StudentDAOInterface getStudentDAO();
    public abstract SubjectCourseDAOInterface getSubjectCourseDAO();
    public abstract TimerDAOInterface getTimerDAO();
    public abstract TitleDAOInterface getTitleDAO();
    public abstract UserDAOInterface getUserDAO();
    public abstract TestDAOAbstract getTestDAO();
    public abstract TestReservationDAOAbstract getTestReservationDAO();
    public abstract SubjectCourseAssignmentDAOInterface getSubjectCourseAssignmentDAO();
}
