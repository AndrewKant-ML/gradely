package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.List;


public class SubjectCourseAssignmentDAOFS implements AbstractSubjectCourseAssignmentDAO {

    private static SubjectCourseAssignmentDAOFS instance;

    private SubjectCourseAssignmentDAOFS() {

    }

    public static synchronized AbstractSubjectCourseAssignmentDAO getInstance() {
        if (instance == null) {
            instance = new SubjectCourseAssignmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course) throws DAOException {
        return null;
    }

    @Override
    public List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException {
        return null; 
    }

    public void insert(SubjectCourseAssignment subjectCourseAssignment){

    }

    public void cancel(SubjectCourseAssignment subjectCourseAssignment){

    }

    public void update(SubjectCourseAssignment subjectCourseAssignment){

    }
}
