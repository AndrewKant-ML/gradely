package it.uniroma2.dicii.ispw.gradely.model.user.dao;

import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class UserDAOAbstract {
    protected static UserDAOAbstract instance;
    protected List<User> registeredUsers;

    protected UserDAOAbstract(){
    }

    public abstract User getUserByEmail(String email);
    public abstract void insert(User user);
    public abstract void update(User user);
    public abstract List<User> refresh(List<User> users);
}
