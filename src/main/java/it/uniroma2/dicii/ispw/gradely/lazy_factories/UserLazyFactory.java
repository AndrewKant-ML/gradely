package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
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
        if (instance == null) {
            instance = new UserLazyFactory();
        }
        return instance;
    }

    public User getUserByEmail(String email) {
        for(User u : registeredUsers){
            if(u.getEmail().equals(email)) {
                return u; //TODO implementare exception
            }
        }
        return DAOFactory.getDAOFactory().getUserDAO().getUserByEmail(email); //TODO implementare exception
    }

}
