package it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractUserDAO;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOFS extends AbstractUserDAO {
    private static UserDAOFS instance;
    private List<User> registeredUsers;

    private UserDAOFS(){ //TODO implementare costruttore vero
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4"));
    }

    public static UserDAOFS getInstance(){
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


}
