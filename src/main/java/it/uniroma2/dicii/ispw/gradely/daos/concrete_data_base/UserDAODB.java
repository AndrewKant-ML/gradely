package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractUserDAO;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAODB extends AbstractUserDAO {
    private List<User> registeredUsers;

    private UserDAODB(){//TODO implementare costruttore vero
        super();
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4"));
    }
    public static synchronized AbstractUserDAO getInstance(){
        if (instance == null) {
            instance = new UserDAODB();
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
