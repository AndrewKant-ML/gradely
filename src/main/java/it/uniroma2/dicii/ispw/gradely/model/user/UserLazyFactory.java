package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;

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

    public User getUserByEmail(String email) throws DAOException, UserNotFoundException {
        for (User u : registeredUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        try {
            return DAOFactoryAbstract.getInstance().getUserDAO().getUserByEmail(email);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
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
