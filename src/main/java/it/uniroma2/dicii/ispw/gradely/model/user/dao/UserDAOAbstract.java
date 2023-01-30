package it.uniroma2.dicii.ispw.gradely.model.user.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class UserDAOAbstract implements DAOInterface <User> {
    protected static UserDAOAbstract instance;

    protected UserDAOAbstract(){
    }

    public abstract User getUserByEmail(String email);
}
