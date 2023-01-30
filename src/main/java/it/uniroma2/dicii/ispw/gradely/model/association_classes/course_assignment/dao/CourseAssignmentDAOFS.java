package it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment.CourseAssignment;

import java.util.ArrayList;
import java.util.List;


public class CourseAssignmentDAOFS extends AbstractCourseAssignmentDAO {
    private CourseAssignmentDAOFS() {
        courseAssignments = new ArrayList<CourseAssignment>();
        courseAssignments.add(new CourseAssignment());
    }

    public static AbstractCourseAssignmentDAO getInstance() {
        if (instance == null) {
            instance = new CourseAssignmentDAOFS();
        }
        return instance;
    }

    @Override
    public CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course) {
        for (CourseAssignment c : courseAssignments) {
            if (c.getSubjectCourse().equals(course)) {
                return c; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) {
        List<CourseAssignment> list = new ArrayList<>();
        for (CourseAssignment c : courseAssignments) {
            if (c.getProfessor().equals(professor)) {
                list.add(c); //TODO implementare exceptions
            }
        }
        return list; //TODO implementare exceptions
    }

    @Override
    public void update(CourseAssignment courseAssignment) {

    }

    @Override
    public List<CourseAssignment> refresh(List<CourseAssignment> courseAssignments) {
        return null;
    }


}
