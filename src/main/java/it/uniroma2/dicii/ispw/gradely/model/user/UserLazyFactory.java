package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserLazyFactory {
    private static UserLazyFactory instance;
    private List<User> registeredUsers;

    private UserLazyFactory(){
        registeredUsers = new ArrayList<User>();
        User user = new User("studente","studenti","stdstd01A02H501Y","s.studenti@uniroma2.it","PWD.difficilissim4");
        user.setRole(new Student(user, "342342", LocalDate.now(), new ArrayList<>()));
        DAOFactoryAbstract.getDAOFactory().getStudentDAO().update(user.getRole().student());
        registeredUsers.add(user);
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
        return DAOFactoryAbstract.getDAOFactory().getUserDAO().getUserByEmail(email); //TODO implementare exception
    }

    public User newUser(String name, String surname, String codiceFiscale, String email, String password){
        User newUser = new User(name, surname,codiceFiscale,email,password);
        DAOFactoryAbstract.getDAOFactory().getUserDAO().insert(newUser);
        registeredUsers.add(newUser);
        return newUser;
    }

}
