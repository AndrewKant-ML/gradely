package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class TitleDAOFS extends TitleDAOAbstract {

    private TitleDAOFS(){
        super();
    }

    @Override
    List<Title> getTitlesByStudent(Student student, List<Title> list) throws UserNotFoundException, DAOException {
        return null;
    }

    public static synchronized TitleDAOAbstract getInstance(){
        if (instance == null){
            instance = new TitleDAOFS();
        }
        return instance;
    }


    @Override
    public void insert(Title title) throws DAOException {

    }

    @Override
    public void cancel(Title title) throws DAOException {

    }

    @Override
    public void update(Title title) throws DAOException {

    }

    @Override
    public List<Title> refresh(List<Title> titles) throws DAOException {
        return null;
    }
}
