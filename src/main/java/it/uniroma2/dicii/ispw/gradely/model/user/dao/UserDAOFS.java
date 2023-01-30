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
        for(User u : registeredUsers){
            if(u.getEmail().equals(email)) {
                return u; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> refresh(List<User> users) {
        return null;
    }


}
