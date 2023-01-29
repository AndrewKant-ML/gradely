package it.uniroma2.dicii.ispw.gradely.daos.user;

import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOFS extends AbstractUserDAO {

    private UserDAOFS(){ //TODO implementare costruttore vero
        registeredUsers = new ArrayList<User>();
        registeredUsers.add(new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4"));
    }

    public static AbstractUserDAO getInstance(){
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
    public void update(User user) {

    }

    @Override
    public List<User> refresh(List<User> users) {
        return null;
    }


}
