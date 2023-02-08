package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserDAOAbstract extends DAOAbstract<User, String> {
    protected static UserDAOAbstract instance;


    protected UserDAOAbstract(){
    }


    abstract User getUserByEmail(String email) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;
    abstract User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException;

    abstract void insert(User user) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(User user) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;
    abstract void setQueryParameters(PreparedStatement stmt, User user) throws SQLException;

}
