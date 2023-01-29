package it.uniroma2.dicii.ispw.gradely.daos.user;

import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.List;

public abstract class AbstractUserDAO {
    protected static AbstractUserDAO instance;
    protected List<User> registeredUsers;

    protected AbstractUserDAO(){ //TODO implementare costruttore vero
    }

    public abstract User getUserByEmail(String email);

    public abstract void update(User user);

    public abstract List<User> refresh(List<User> users);

}
