package it.uniroma2.dicii.ispw.gradely.model.user.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public abstract class UserDAOAbstract implements DAOInterface <User> {
    protected static UserDAOAbstract instance;

    protected UserDAOAbstract(){
    }

    public abstract User getUserByEmail(String email) throws UserNotFoundException, DAOException;
}
