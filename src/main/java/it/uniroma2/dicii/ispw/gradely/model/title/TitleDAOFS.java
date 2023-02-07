package it.uniroma2.dicii.ispw.gradely.model.title;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.util.List;

public class TitleDAOFS extends TitleDAOAbstract {

    private TitleDAOFS(){
        super();
    }


    public static synchronized TitleDAOAbstract getInstance(){
        if (instance == null){
            instance = new TitleDAOFS();
        }
        return instance;
    }

    @Override
    List<Title> getTitlesByStudent(Student student, List<Title> list) throws PropertyException, ResourceNotFoundException, DAOException {
        return null;
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

}
