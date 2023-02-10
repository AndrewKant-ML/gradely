package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserLazyFactory {
    private static UserLazyFactory instance;
    private final List<User> registeredUsers;

    private UserLazyFactory() {
        registeredUsers = new ArrayList<>();
    }

    public static synchronized UserLazyFactory getInstance() {
        if (instance == null) {
            instance = new UserLazyFactory();
        }
        return instance;
    }

    /**
     * Gets a User by its email
     *
     * @param email the User's email
     * @return a User object
     * @throws DAOException              thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading db connection properties OR thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     * @throws UserNotFoundException thrown if the email does not match any User
     */
    public User getUserByEmail(String email) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException {
        for (User u : registeredUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        User daoUser = DAOFactoryAbstract.getInstance().getUserDAO().getUserByEmail(email);
        registeredUsers.add(daoUser);
        return daoUser;
    }

    public User getUserByCodiceFiscale(String codiceFiscale) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException {
        for (User u : registeredUsers) {
            if (u.getCodiceFiscale().equals(codiceFiscale)) {
                return u;
            }
        }
        User daoUser = DAOFactoryAbstract.getInstance().getUserDAO().getUserByCodiceFiscale(codiceFiscale);
        registeredUsers.add(daoUser);
        return daoUser;
    }

    public User newUser(String name, String surname, String codiceFiscale, String email, String password, LocalDate registrationDate) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        User newUser = new User(name, surname, codiceFiscale, email, password, registrationDate);
            DAOFactoryAbstract.getInstance().getUserDAO().insert(newUser);
            registeredUsers.add(newUser);
            return newUser;
    }
}
