package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;

import java.util.List;

public class UserDAOFS extends UserDAOAbstract {

    private UserDAOFS(){
        super();
    }

    public static synchronized UserDAOAbstract getInstance(){
        if (instance == null){
            instance = new UserDAOFS();
        }
        return instance;
    }

    User getUserByEmail(String email)  throws UserNotFoundException, DAOException {
        //TODO implementare query
        return null;
    }

    @Override
    public void insert(User user) throws DAOException{
        //TODO implementare query
    }

    @Override
    public void cancel(User user) throws DAOException{

    }

    @Override
    public void update(User user) throws DAOException{
        //TODO implementare query
    }

    @Override
    public List<User> refresh(List<User> users) throws DAOException{
        //TODO implementare query
        return null;
    }


}
