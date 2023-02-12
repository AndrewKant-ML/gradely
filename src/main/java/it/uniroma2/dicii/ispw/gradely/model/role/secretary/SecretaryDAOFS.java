package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import com.opencsv.exceptions.CsvException;
import it.uniroma2.dicii.ispw.gradely.CSVParser;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.util.ArrayList;
import java.util.List;

public class
SecretaryDAOFS implements AbstractSecretaryDAO {

    private static SecretaryDAOFS instance;

    private final String filename = "secretary";

    private SecretaryDAOFS() {

    }

    public static synchronized AbstractSecretaryDAO getInstance() {
        if (instance == null) {
            instance = new SecretaryDAOFS();
        }
        return instance;
    }

    @Override
    public Secretary getSecretaryByUser(User user) throws DAOException, ResourceNotFoundException, UserNotFoundException, PropertyException {
        try {
            List<List<String>> lines = new CSVParser().readAllLines(filename);
            for (List<String> line : lines) {
                if (line.get(0).equals(user.getCodiceFiscale()))
                    return new Secretary(
                            user,
                            DipartimentoEnum.getDipartimentoByValue(Integer.parseInt(line.get(1)))
                    );
            }
            throw new UserNotFoundException(ExceptionMessagesEnum.SECRETARY_NOT_FOUND.message);
        } catch (CsvException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> secretaryList) throws DAOException, UserNotFoundException, ResourceNotFoundException, PropertyException, MissingAuthorizationException {
        try {
            List<Secretary> secretaries = new ArrayList<>();
            List<List<String>> lines = new CSVParser().readAllLines(filename);
            for (List<String> line : lines) {
                if (line.get(1).equals(String.valueOf(dipartimento.value))
                        && checkListPresenceByCodiceFiscale(line.get(0), secretaryList))
                    secretaries.add(UserLazyFactory.getInstance().getUserByCodiceFiscale(line.get(0)).getRole().getSecretaryRole());
            }
            return secretaries;
        } catch (CsvException | WrongListQueryIdentifierValue | ObjectNotFoundException | UnrecognizedRoleException |
                 WrongDegreeCourseCodeException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Checks if a Secretary is contained in a List by checking each list's element by codice fiscale
     *
     * @param codiceFiscale the codice fiscale to be checked
     * @param secretaries   the List of Secretary to check on
     * @return true if the Secretary is contained in the list, false otherwise
     */
    private boolean checkListPresenceByCodiceFiscale(String codiceFiscale, List<Secretary> secretaries) {
        for (Secretary secretary : secretaries)
            if (secretary.getCodiceFiscale().equals(codiceFiscale))
                return true;
        return false;
    }

    public void insert(Secretary secretary) {
        // To be implemented
    }

    public void delete(Secretary secretary) {
        // To be implemented
    }

    public void update(Secretary secretary){
        // To be implemented
    }
}
