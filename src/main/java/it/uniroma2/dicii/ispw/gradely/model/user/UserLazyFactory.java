package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserLazyFactory {
    private static UserLazyFactory instance;
    private final List<User> registeredUsers;

    private UserLazyFactory() {
        registeredUsers = new ArrayList<User>();
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
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the email does not match any User
     */
    public User getUserByEmail(String email) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        for (User u : registeredUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        User DAOUser = DAOFactoryAbstract.getInstance().getUserDAO().getUserByEmail(email);
        registeredUsers.add(DAOUser);
        return DAOUser;
    }

    public User getUserByCodiceFiscale(String codiceFiscale) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        for (User u : registeredUsers)
            if (u.getCodiceFiscale().equals(codiceFiscale))
                return u;
        return DAOFactoryAbstract.getInstance().getUserDAO().getUserByCodiceFiscale(codiceFiscale);
    }

    public User newUser(String name, String surname, String codiceFiscale, String email, String password, LocalDate registrationDate) throws DAOException {
        User newUser = new User(name, surname, codiceFiscale, email, password, registrationDate);
        try {
            DAOFactoryAbstract.getInstance().getUserDAO().insert(newUser);
            registeredUsers.add(newUser);
            return newUser;
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}
