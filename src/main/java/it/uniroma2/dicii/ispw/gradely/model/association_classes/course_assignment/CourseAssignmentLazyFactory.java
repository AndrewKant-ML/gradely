package it.uniroma2.dicii.ispw.gradely.model.association_classes.course_assignment;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class CourseAssignmentLazyFactory {
    private static CourseAssignmentLazyFactory instance;
    private List<CourseAssignment> courseAssignments;

    private CourseAssignmentLazyFactory(){
        courseAssignments = new ArrayList<CourseAssignment>();
    }

    public static synchronized CourseAssignmentLazyFactory getInstance(){
        if (instance == null){
            instance = new CourseAssignmentLazyFactory();
        }
        return instance;
    }

    public CourseAssignment getCourseAssignmentBySubjectCourse(SubjectCourse course) throws DAOException {
        for(CourseAssignment c : courseAssignments){
            if(c.getSubjectCourse().equals(course)){
                return c;
            }
        }
        return DAOFactoryAbstract.getInstance().getCourseAssignmentDAO().getCourseAssignmentBySubjectCourse(course);
    }

    public List<CourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException {
        List<CourseAssignment> list = new ArrayList<>();
        for(CourseAssignment c : courseAssignments){
            if(c.getProfessor().equals(professor)){
                list.add(c);
            }
        }
        List<CourseAssignment> daoList = DAOFactoryAbstract.getInstance().getCourseAssignmentDAO().getCourseAssignmentsByProfessor(professor);
        for(CourseAssignment c : daoList){
            if(!list.contains(c)){
                list.add(c);
            }
        }
        return list;
    }
    public List<SubjectCourse> getAssignedSubjectCoursesByProfessor(Professor professor) throws DAOException {
        List<SubjectCourse> list = new ArrayList<>();
        for(CourseAssignment c : courseAssignments){
            if(c.getProfessor().equals(professor)){
                list.add(c.getSubjectCourse()); 
            }
        }
        List<CourseAssignment> daoList = DAOFactoryAbstract.getInstance().getCourseAssignmentDAO().getCourseAssignmentsByProfessor(professor);
        for(CourseAssignment c : daoList){
            if(!list.contains(c.getSubjectCourse())){
                list.add(c.getSubjectCourse());
            }
        }
        return list;
    }
}