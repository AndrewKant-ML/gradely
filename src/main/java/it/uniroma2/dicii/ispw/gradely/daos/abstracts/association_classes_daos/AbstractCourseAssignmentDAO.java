package it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.CourseAssignment;

import java.util.List;


public abstract class AbstractCourseAssignmentDAO {
    protected static AbstractCourseAssignmentDAO instance;

    protected AbstractCourseAssignmentDAO() { //TODO implementare costruttore vero
    }

    public abstract CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course);

    public abstract List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor);


}
