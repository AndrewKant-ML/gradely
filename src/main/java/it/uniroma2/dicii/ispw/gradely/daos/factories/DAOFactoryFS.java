package it.uniroma2.dicii.ispw.gradely.daos.factories;

import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.course_assignment.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment.AbstractDegreeCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.exam_enrollment.AbstractExamEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.subject_course_enrollment.AbstractSubjectCourseEnrollmentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.course_assignment.CourseAssignmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.degree_course_enrollment.DegreeCourseEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.exam_enrollment.ExamEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.subject_course_enrollment.SubjectCourseEnrollmentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.degree_course.AbstractDegreeCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.degree_course.DegreeCourseDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.exam.AbstractExamDAO;
import it.uniroma2.dicii.ispw.gradely.daos.exam.ExamDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.pending_event.AbstractPendingEventDAO;
import it.uniroma2.dicii.ispw.gradely.daos.pending_event.PendingEventDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.professor.AbstractProfessorDAO;
import it.uniroma2.dicii.ispw.gradely.daos.professor.ProfessorDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.secretary.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.daos.secretary.SecretaryDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.student.AbstractStudentDAO;
import it.uniroma2.dicii.ispw.gradely.daos.student.StudentDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.subject_course.AbstractSubjectCourseDAO;
import it.uniroma2.dicii.ispw.gradely.daos.subject_course.SubjectCourseDAOFS;
import it.uniroma2.dicii.ispw.gradely.daos.user.AbstractUserDAO;
import it.uniroma2.dicii.ispw.gradely.daos.user.UserDAOFS;

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
