package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class ProfessorDAOFS implements AbstractProfessorDAO {

    private static ProfessorDAOFS instance;

    private final String fileName = "professor";

    private ProfessorDAOFS() {
    }

    public static synchronized AbstractProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAOFS();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, ResourceNotFoundException {
        /*try {
            List<List<String>> lines = new CSVParser().readAllLines(fileName);
            for (List<String> line : lines)
                if (line.get(0).equals(user.getCodiceFiscale())) {
                    Professor professor = new Professor(
                            user,
                            line.get(1),
                            DipartimentoEnum.getDipartimentoByValue(Integer.parseInt(line.get(2)))
                    );
                    setProfessorData(professor);
                    return professor;
                }
            throw new UserNotFoundException(ExceptionMessagesEnum.USER_NOT_FOUND.message);
        } catch (CsvException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }*/
        return null;
    }

    public void insert(Professor professor) throws DAOException {

    }

    public void delete(Professor professor) throws DAOException {

    }

    public void update(Professor professor) throws DAOException {
    }

}
