package it.uniroma2.dicii.ispw.gradely.model.user;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class UserDAOFS extends UserDAOAbstract {

    private final String fileName = "student";

    private UserDAOFS() {
        super();
    }

    public static synchronized UserDAOAbstract getInstance() {
        if (instance == null) {
            instance = new UserDAOFS();
        }
        return instance;
    }

    User getUserByEmail(String email) throws UserNotFoundException, DAOException {
        //TODO implementare query
        return null;
    }

    @Override
    User getUserByCodiceFiscale(String codiceFiscale) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException {
        try {
            Map<String, String> lineValues;
            CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(new CSVParser().buildResourceReader(fileName));
            do
                lineValues = csvReader.readMap();
            while (!lineValues.get("codice_fiscale").equals(codiceFiscale));
            return new User(
                    lineValues.get("name"),
                    lineValues.get("surname"),
                    codiceFiscale,
                    lineValues.get("email"),
                    lineValues.get("password"),
                    LocalDate.parse(lineValues.get("registration_date"))
            );
        } catch (CsvException | IOException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(User user) throws DAOException {
        //TODO implementare query
    }

    @Override
    public void cancel(User user) throws DAOException {

    }

    @Override
    public void update(User user) throws DAOException {
        //TODO implementare query
    }

}
