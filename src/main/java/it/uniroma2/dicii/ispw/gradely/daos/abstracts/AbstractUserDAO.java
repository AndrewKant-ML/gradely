package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base.UserDAODB;
import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUserDAO {
    protected static AbstractUserDAO instance;
    private List<User> registeredUsers;

    protected AbstractUserDAO(){ //TODO implementare costruttore vero
    }

    public abstract User getUserByEmail(String email);







}
