package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class ProfessorDAOFS extends AbstractProfessorDAO {

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
        try {
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
        }
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

}
