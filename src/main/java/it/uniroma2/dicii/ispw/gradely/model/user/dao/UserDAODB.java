package it.uniroma2.dicii.ispw.gradely.model.user.dao;

import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class UserDAODB extends UserDAOAbstract {

    private UserDAODB(){
        super();
    }
    public static synchronized UserDAOAbstract getInstance(){
        if (instance == null) {
            instance = new UserDAODB();
        }
        return instance;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void cancel(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> refresh(List<User> users) {
        return null;
    }


}
