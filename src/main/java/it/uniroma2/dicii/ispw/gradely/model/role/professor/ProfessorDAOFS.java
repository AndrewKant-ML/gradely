package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class ProfessorDAOFS extends AbstractProfessorDAO {

    private ProfessorDAOFS(){ 
}

    public static synchronized AbstractProfessorDAO getInstance(){
        if (instance == null){
            instance = new ProfessorDAOFS();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        return null;
    }

    @Override
    public void insert(Professor professor) throws DAOException {

    }

    @Override
    public void cancel(Professor professor) throws DAOException {

    }

    @Override
    public void update(Professor professor) throws DAOException {

    }

    @Override
    public List<Professor> refresh(List<Professor> professors) throws DAOException {
        return null;
    }
}
