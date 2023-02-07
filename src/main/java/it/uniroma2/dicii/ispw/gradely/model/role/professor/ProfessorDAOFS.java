package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        try {
            Map<String, String> lineValues;
            CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(new CSVParser().buildResourceReader(fileName));
            do
                lineValues = csvReader.readMap();
            while (!lineValues.get("codice_fiscale").equals(user.getCodiceFiscale()));
            Professor professor = new Professor(
                    user,
                    lineValues.get("matricola"),
                    DipartimentoEnum.getDipartimentoByValue(Integer.parseInt(lineValues.get("dipartimento")))
            );
            setProfessorData(professor);
            return professor;
        } catch (IOException | CsvValidationException e) {
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
