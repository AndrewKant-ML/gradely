package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

public abstract class UserDAOAbstract implements DAOInterface <User> {
    protected static UserDAOAbstract instance;

    protected UserDAOAbstract(){
    }

    abstract User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;

    abstract User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;
}
