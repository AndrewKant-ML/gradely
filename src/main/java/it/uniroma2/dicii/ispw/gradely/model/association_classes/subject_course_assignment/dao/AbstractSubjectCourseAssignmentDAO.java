package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public abstract class AbstractSubjectCourseAssignmentDAO implements DAOInterface<SubjectCourseAssignment> {
    protected static AbstractSubjectCourseAssignmentDAO instance;

    protected AbstractSubjectCourseAssignmentDAO(){
    }

    public abstract List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course) throws DAOException;

    public abstract List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException ;

}
