package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class ProfessorDAOFS implements AbstractProfessorDAO {

    private static ProfessorDAOFS instance;

    private static final String fileName = "professor";

    private ProfessorDAOFS() {
    }

    public static synchronized AbstractProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAOFS();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) {
        return null;
    }

    public void insert(Professor professor) throws DAOException {
        // To be implemented
    }

    public void delete(Professor professor) throws DAOException {
        // To be implemented
    }

    public void update(Professor professor) throws DAOException {
        // To be implemented
    }

}
