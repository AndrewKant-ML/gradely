package it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public abstract class AbstractCourseAssignmentDAO implements DAOInterface<CourseAssignment> {
    protected static AbstractCourseAssignmentDAO instance;

    protected AbstractCourseAssignmentDAO(){
    }

    public abstract CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course) throws DAOException;

    public abstract List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException ;

}
