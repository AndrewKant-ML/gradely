package it.uniroma2.dicii.ispw.gradely.model.user.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.role.student.StudentLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAODB extends UserDAOAbstract {

    private UserDAODB(){
        super();
    }
    public static synchronized UserDAOAbstract getInstance(){
        if (instance == null){
            instance = new UserDAODB();
        }
        return instance;
    }

    public User getUserByEmail(String email)  throws UserNotFoundException, DAOException {
        Student student = StudentLazyFactory.getInstance().newStudent("Andrea", "Cantarini", "CNT", email, "aaa", "0294136", LocalDate.now(), new ArrayList<>());
        return student.getUser();
    }

    @Override
    public void insert(User user){

    }

    @Override
    public void cancel(User user){

    }

    @Override
    public void update(User user){

    }

    @Override
    public List<User> refresh(List<User> users){
        return null;
    }


}
