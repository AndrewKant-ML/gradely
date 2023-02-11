package it.uniroma2.dicii.ispw.gradely.model.association_classes.degree_course_enrollment;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.degree_course.DegreeCourse;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public interface DegreeCourseEnrollmentDAOInterface {

    List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByDegreeCourse(DegreeCourse course, List<DegreeCourseEnrollment> excluded) throws DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException;

    List<DegreeCourseEnrollment> getDegreeCourseEnrollmentsByStudent(Student student, List<DegreeCourseEnrollment> exclusions) throws DAOException, PropertyException, ResourceNotFoundException, WrongDegreeCourseCodeException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException;

    /**
     * Inserts an object into DB
     * @param degreeCourseEnrollment the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(DegreeCourseEnrollment degreeCourseEnrollment) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from DB
     * @param degreeCourseEnrollment the object to cancel
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void cancel(DegreeCourseEnrollment degreeCourseEnrollment) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in DB to its current state
     * @param degreeCourseEnrollment the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(DegreeCourseEnrollment degreeCourseEnrollment) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

}