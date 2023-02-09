package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;


public interface UserDAOInterface {

    /**
     * Retrieves a User with a given email
     *
     * @param email the User's email
     * @return a User object
     * @throws UserNotFoundException thrown if the User cannot be found
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException;

    /**
     * Retrieves a User with a given codice fiscale
     *
     * @param codiceFiscale the User's codice fiscale
     * @return a User object
     * @throws UserNotFoundException thrown if the User cannot be found
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     */
    User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException;

    /**
     * Inserts an object into the DB
     * @param user the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from the DB
     * @param user the object to cancel
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param user the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    void update(User user) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;


}
