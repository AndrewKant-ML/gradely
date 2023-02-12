package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.ArrayList;
import java.util.List;

public class DegreeCourseEnrollmentLazyFactory {
    private static DegreeCourseEnrollmentLazyFactory instance;
    private final List<DegreeCourseEnrollment> factoryObjects;

    private DegreeCourseEnrollmentLazyFactory(){
        factoryObjects = new ArrayList<>();
    }

    public static synchronized DegreeCourseEnrollmentLazyFactory getInstance(){
        if (instance == null){
            instance = new DegreeCourseEnrollmentLazyFactory();
        }
        return instance;
    }

    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course) throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        List<DegreeCourseEnrollment> list = new ArrayList<>();
        for (DegreeCourseEnrollment e : factoryObjects) {
            if (e.getDegreeCourse().equals(course)) {
                list.add(e);
            }
        }
        try {
            list.addAll(DAOFactoryAbstract.getInstance().getDegreeCourseEnrollmentDAO().getDegreeCourseEnrollmentsByDegreeCourse(course,list));
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return list;
    }

    /**
     * Retrieves all the DegreeCourseEnrollment of a given Student,
     * first by searching in the loaded list, then by querying the persistence layer
     *
     * @param student the Student whose DegreeCourseEnrollment have to be retrieved
     * @return a List of DegreeCourseEnrollment objects
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    public List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student) throws DAOException, WrongDegreeCourseCodeException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, PropertyException, ResourceNotFoundException {
        List<DegreeCourseEnrollment> list = new ArrayList<>();
        for (DegreeCourseEnrollment e : factoryObjects) {
            if (e.getStudent().equals(student)) {
                list.add(e);
            }
        }
        try {
            list.addAll(DAOFactoryAbstract.getInstance().getDegreeCourseEnrollmentDAO().getDegreeCourseEnrollmentsByStudent(student, list));
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return list;
    }

    public Boolean checkDegreeCourseEnrollmentPresence(Student student, DegreeCourse course) throws DAOException, WrongDegreeCourseCodeException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, PropertyException, ResourceNotFoundException {
        List<DegreeCourseEnrollment> list = getDegreeCourseEnrollmentsByStudent(student);
        for(DegreeCourseEnrollment e : list){
            if(e.getDegreeCourse().equals(course)){
                return true; 
            }
        }
        return false;
    }
}