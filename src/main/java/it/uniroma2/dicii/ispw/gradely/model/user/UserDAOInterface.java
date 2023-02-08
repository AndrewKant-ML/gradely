package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;


public interface UserDAOInterface {


    User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;
    User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;


}
