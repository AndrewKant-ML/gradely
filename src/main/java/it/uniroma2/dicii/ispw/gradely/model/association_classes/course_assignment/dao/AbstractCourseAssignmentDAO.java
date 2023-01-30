package it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;

import java.util.List;


public abstract class AbstractCourseAssignmentDAO {
    protected static AbstractCourseAssignmentDAO instance;
    protected List<CourseAssignment> courseAssignments;

    protected AbstractCourseAssignmentDAO() {
    }

    public abstract CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course);

    public abstract List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor);

    public abstract void update(CourseAssignment courseAssignment);

    public abstract List<CourseAssignment> refresh(List<CourseAssignment> courseAssignments);


}
