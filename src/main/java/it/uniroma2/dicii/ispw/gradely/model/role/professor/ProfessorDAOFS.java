package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOFS implements AbstractProfessorDAO {

    private static ProfessorDAOFS instance;

    private static final String FILE_NAME = "professor";

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

    @Override
    public List<Professor> getProfessorsByDipartimento(DipartimentoEnum dipartimento, List<Professor> professors) {
        return new ArrayList<>();
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
