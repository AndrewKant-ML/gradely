package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.model.User;

public abstract class AbstractUserDAO {
    protected static AbstractUserDAO instance;

    protected AbstractUserDAO(){ //TODO implementare costruttore vero
    }

    public abstract User getUserByEmail(String email);







}
