package it.uniroma2.dicii.ispw.gradely.daos.association_classes_daos.course_assignment;

import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.CourseAssignment;

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
