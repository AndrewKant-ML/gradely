package it.uniroma2.dicii.ispw.gradely.daos.user;

import it.uniroma2.dicii.ispw.gradely.model.Student;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAODB extends AbstractUserDAO {

    private UserDAODB(){//TODO implementare costruttore vero
        super();
        registeredUsers = new ArrayList<User>();
        User user = new User("mario","rossi","RSSMRI01A02H501Y","m.rossi@uniroma2.it","PWD.difficilissim4");
        user.setRole(new Student(user, "342342", LocalDate.now(), new ArrayList<>()));
        registeredUsers.add(user);
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

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> refresh(List<User> users) {
        return null;
    }


}
