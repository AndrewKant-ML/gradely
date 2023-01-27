package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static UserDAO instance;
    private List<User> registeredUsers;

    private UserDAO(){ //TODO implementare costruttore vero
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4"));
    }

    public static UserDAO getInstance(){
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User getUser(String email) {
        for(User u : registeredUsers){
            if(u.getEmail().equals(email)) {
                return u; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }


}
