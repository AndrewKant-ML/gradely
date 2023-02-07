package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;

public abstract class UserDAOAbstract extends DAOAbstract<User> {
    protected static UserDAOAbstract instance;

    abstract void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(User user) throws DAOException, PropertyException, ResourceNotFoundException;

    protected UserDAOAbstract(){
    }

    abstract User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;

    abstract User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;
}
