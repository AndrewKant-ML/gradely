package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.association_classes_daos;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.association_classes_daos.AbstractCourseAssignmentDAO;
import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.SubjectCourse;
import it.uniroma2.dicii.ispw.gradely.model.association_classes.CourseAssignment;

import java.util.ArrayList;
import java.util.List;


public class CourseAssignmentDAODB extends AbstractCourseAssignmentDAO {
    private static CourseAssignmentDAODB instance;
    private List<CourseAssignment> courseAssignments;

    private CourseAssignmentDAODB() { //TODO implementare costruttore vero
        courseAssignments = new ArrayList<CourseAssignment>();
        courseAssignments.add(new CourseAssignment());
    }

    public static CourseAssignmentDAODB getInstance() {
        if (instance == null) {
            instance = new CourseAssignmentDAODB();
        }
        return instance;
    }

    public CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course) {
        for (CourseAssignment c : courseAssignments) {
            if (c.getSubjectCourse().equals(course)) {
                return c; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }

    public List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) {
        List<CourseAssignment> list = new ArrayList<>();
        for (CourseAssignment c : courseAssignments) {
            if (c.getProfessor().equals(professor)) {
                list.add(c); //TODO implementare exceptions
            }
        }
        return list; //TODO implementare exceptions
    }


}
