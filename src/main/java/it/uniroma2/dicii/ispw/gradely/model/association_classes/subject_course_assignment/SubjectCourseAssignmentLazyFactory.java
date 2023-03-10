package it.uniroma2.dicii.ispw.gradely.model.association_classes.subject_course_assignment;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.subject_course.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

public class SubjectCourseAssignmentLazyFactory {
    private static SubjectCourseAssignmentLazyFactory instance;
    private final List<SubjectCourseAssignment> factoryObjects;

    private SubjectCourseAssignmentLazyFactory() {
        factoryObjects = new ArrayList<SubjectCourseAssignment>();
    }

    public static synchronized SubjectCourseAssignmentLazyFactory getInstance() {
        if (instance == null) {
            instance = new SubjectCourseAssignmentLazyFactory();
        }
        return instance;
    }

    public List<SubjectCourseAssignment> getCourseAssignmentsBySubjectCourse(SubjectCourse course) throws DAOException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        List<SubjectCourseAssignment> list = new ArrayList<>();
        for (SubjectCourseAssignment c : factoryObjects) {
            if (c.getSubjectCourse().equals(course)) {
                list.add(c);
            }
        }
        try {
            List<SubjectCourseAssignment> daoList = DAOFactoryAbstract.getInstance().getCourseAssignmentDAO().getCourseAssignmentsBySubjectCourse(course,list);
            factoryObjects.addAll(daoList);
            list.addAll(daoList);
            return list;
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public List<SubjectCourseAssignment> getCourseAssignmentsByProfessor(Professor professor) throws DAOException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        List<SubjectCourseAssignment> list = new ArrayList<>();
        for (SubjectCourseAssignment c : factoryObjects) {
            if (c.getProfessor().equals(professor)) {
                list.add(c);
            }
        }
        try {
            List<SubjectCourseAssignment> daoList = DAOFactoryAbstract.getInstance().getCourseAssignmentDAO().getCourseAssignmentsByProfessor(professor,list);
            factoryObjects.addAll(daoList);
            list.addAll(daoList);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return list;
    }

    public List<SubjectCourse> getAssignedSubjectCoursesByProfessor(Professor professor) throws DAOException, UserNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        List<SubjectCourse> list = new ArrayList<>();
        for (SubjectCourseAssignment a : getCourseAssignmentsByProfessor(professor)) {
            list.add(a.getSubjectCourse());
        }
        return list;
    }
}