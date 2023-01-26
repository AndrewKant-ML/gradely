package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.UserDAO;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserLazyFactory {
    private static UserLazyFactory instance;
    private List<User> registeredUsers;

    private UserLazyFactory(){
        registeredUsers = new ArrayList<User>();
    }

    public static UserLazyFactory getInstance(){
        if (instance!=null){
            return instance;
        }
        else return new UserLazyFactory();
    }

    public User getUser(String email) {
        for(User u : registeredUsers){
            if(u.getEmail().equals(email)) {
                return u; //TODO implementare exception
            }
        }
        return UserDAO.getInstance().getUser(email); //TODO implementare exception
    }

    public void addRegisteredUser(User user) {
        this.registeredUsers.add(user);
    }
}
