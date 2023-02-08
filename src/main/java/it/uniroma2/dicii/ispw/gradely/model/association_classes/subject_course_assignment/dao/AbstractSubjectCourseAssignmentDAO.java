package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment.SubjectCourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public abstract class AbstractSubjectCourseAssignmentDAO implements DAODBAbstract<SubjectCourseAssignment> {
    protected static AbstractSubjectCourseAssignmentDAO instance;

    protected AbstractSubjectCourseAssignmentDAO(){
    }

    public abstract List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException;

    public abstract List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException;

}
