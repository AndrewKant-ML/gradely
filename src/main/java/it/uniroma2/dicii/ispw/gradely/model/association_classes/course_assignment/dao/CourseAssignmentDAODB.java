package it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public class CourseAssignmentDAODB extends AbstractCourseAssignmentDAO {
    private CourseAssignmentDAODB(){

    }

    public static synchronized AbstractCourseAssignmentDAO getInstance(){
        if (instance == null){
            instance = new CourseAssignmentDAODB();
        }
        return instance;
    }

    @Override
    public CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course) throws DAOException {
        return null;
    }

    @Override
    public List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException {
        return null;
    }

    @Override
    public void insert(CourseAssignment courseAssignment){

    }

    @Override
    public void cancel(CourseAssignment courseAssignment){

    }

    @Override
    public void update(CourseAssignment courseAssignment){

    }

    @Override
    public List<CourseAssignment> refresh(List<CourseAssignment> courseAssignments){
        return null;
    }


}
