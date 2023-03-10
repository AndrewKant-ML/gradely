package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;


public class SubjectCourseAssignmentDAOFS implements SubjectCourseAssignmentDAOInterface {

    private static SubjectCourseAssignmentDAOFS instance;

    private SubjectCourseAssignmentDAOFS() {

    }

    public static synchronized SubjectCourseAssignmentDAOInterface getInstance() {
        if (instance == null) {
            instance = new SubjectCourseAssignmentDAOFS();
        }
        return instance;
    }

    @Override
    public List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course, List<SubjectCourseAssignment> exclusions) throws DAOException {
        return new ArrayList<>();
    }

    @Override
    public List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor, List<SubjectCourseAssignment> exclusions) throws DAOException {
        return new ArrayList<>();
    }

    public void insert(SubjectCourseAssignment subjectCourseAssignment){
        // To be implemented
    }

    public void delete(SubjectCourseAssignment subjectCourseAssignment){
        // To be implemented
    }

    public void update(SubjectCourseAssignment subjectCourseAssignment){
        // To be implemented
    }
}
