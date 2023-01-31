package it.uniroma2.dicii.ispw.gradely.model.user.dao;

import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class UserDAOFS extends UserDAOAbstract {

    private UserDAOFS(){
        super();
    }

    public static UserDAOAbstract getInstance(){
        if (instance == null) {
            instance = new UserDAOFS();
        }
        return instance;
    }

    public User getUserByEmail(String email) {
        //TODO implementare query
        return null;
    }

    @Override
    public void insert(User user) {
        //TODO implementare query
    }

    @Override
    public void cancel(User user) {

    }

    @Override
    public void update(User user) {
        //TODO implementare query
    }

    @Override
    public List<User> refresh(List<User> users) {
        //TODO implementare query
        return null;
    }


}
