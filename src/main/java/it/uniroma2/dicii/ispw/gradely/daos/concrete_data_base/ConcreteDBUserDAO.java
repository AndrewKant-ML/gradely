package it.uniroma2.dicii.ispw.gradely.daos.concrete_data_base;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractUserDAO;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class ConcreteDBUserDAO extends AbstractUserDAO {
    private static ConcreteDBUserDAO instance;
    private List<User> registeredUsers;

    private ConcreteDBUserDAO(){
        super(); //TODO implementare costruttore vero
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4"));
    }

    public static ConcreteDBUserDAO getInstance(){
        if (instance == null) {
            instance = new ConcreteDBUserDAO();
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
