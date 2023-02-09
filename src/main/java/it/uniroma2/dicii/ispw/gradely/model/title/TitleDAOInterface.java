package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public interface TitleDAOInterface {
    /**
     * Retrieves all Title of a given Student
     *
     * @param student   the Student whose titles have to be found
     * @param list the titles already loaded in memory
     * @return a List of Title objects
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    List<Title> getTitlesByStudent(Student student, List<Title> list) throws PropertyException, ResourceNotFoundException, DAOException, UserNotFoundException, MissingAuthorizationException, UnrecognizedRoleException;

    /**
     * Inserts an object into the DB
     * @param title the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(Title title) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Deletes an object from the DB
     * @param title the object to cancel
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void cancel(Title title) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param title the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(Title title) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;


}
