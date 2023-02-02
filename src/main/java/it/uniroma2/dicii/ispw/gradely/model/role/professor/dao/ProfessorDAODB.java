package it.uniroma2.dicii.ispw.gradely.model.role.professor.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class ProfessorDAODB extends AbstractProfessorDAO {

    private ProfessorDAODB(){ //TODO implementare costruttore vero

    }

    public static AbstractProfessorDAO getInstance(){
        if (instance == null) {
            instance = new ProfessorDAODB();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) throws DAOException {
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
