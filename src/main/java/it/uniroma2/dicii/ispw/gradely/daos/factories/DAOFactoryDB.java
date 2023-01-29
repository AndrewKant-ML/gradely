package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.course_assignment.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.exam_enrollment.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.subject_course_enrollment.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.course_assignment.CourseAssignmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment.DegreeCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.exam_enrollment.ExamEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.subject_course_enrollment.SubjectCourseEnrollmentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.degree_course.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.degree_course.DegreeCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.exam.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.daos.exam.ExamDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.pending_event.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.daos.pending_event.PendingEventDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.daos.professor.ProfessorDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.daos.secretary.SecretaryDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.student.AbstractStudentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.student.StudentDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.subject_course.AbstractSubjectCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.subject_course.SubjectCourseDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.user.AbstractUserDAO;
import it.uniroma2.dicii.ispw.gradely.daos.user.UserDAODB;

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
