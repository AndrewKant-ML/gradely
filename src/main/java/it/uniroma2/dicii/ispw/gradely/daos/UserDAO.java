package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static UserDAO instance;
    private User user;
    private List<User> registeredUsers;

    private UserDAO(){
        user = new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4");
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(user);
    }

    public static UserDAO getInstance(){
        if (instance!=null){
            return instance;
        }
        else return new UserDAO();
    }

    public User getUser(String email) {
        for(User u : registeredUsers){
            if(u.getEmail().equals(email)) {
                return u; //TODO implementare exceptions
            }
        }
        return null; //TODO implementare exceptions
    }

    public void setRegisteredUsers(List<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public void addRegisteredUser(User user) {
        this.registeredUsers.add(user);
    }

}
