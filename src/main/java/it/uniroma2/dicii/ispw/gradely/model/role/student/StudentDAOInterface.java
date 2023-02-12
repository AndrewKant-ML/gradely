package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public interface StudentDAOInterface {
    /**
     * Inserts an object into the DB
     * @param student the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(Student student) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from the DB
     * @param student the object to delete
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void delete(Student student) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param student the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(Student student) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    abstract Student getStudentByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue;
}