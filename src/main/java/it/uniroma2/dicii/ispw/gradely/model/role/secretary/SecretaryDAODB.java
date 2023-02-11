package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.instances_management_abstracts.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SecretaryDAODB  extends DAODBAbstract<Secretary> implements AbstractSecretaryDAO {
    protected static AbstractSecretaryDAO instance;

    private SecretaryDAODB() {

    }

    public static synchronized AbstractSecretaryDAO getInstance() {
        if (instance == null) {
            instance = new SecretaryDAODB();
        }
        return instance;
    }

    /**
     * Retrieve all Secretary's data of a given User
     *
     * @param user the User whose Secretary's data have to be retrieved
     * @return a Secretary object
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Student role
     */
    @Override
    public Secretary getSecretaryByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException, WrongListQueryIdentifierValue {
        return getQuery(
                "SECRETARY",
                List.of("codice_fiiscale"),
                List.of(user.getCodiceFiscale()),
                List.of(user)
        );
    }

    /**
     * Gets all the Secretaries of a given dipartimento
     *
     * @param dipartimento  the dipartimento whose secretaries have to be found
     * @param excludedList the secretaries already loaded in memory
     * @return a List of Secretary objects
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Secretary role
     */
    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> excludedList) throws DAOException, UserNotFoundException, MissingAuthorizationException, PropertyException, ResourceNotFoundException, ObjectNotFoundException, UnrecognizedRoleException, WrongListQueryIdentifierValue, WrongDegreeCourseCodeException {
        return getListQuery(
                "SECRETARY",
                List.of("dipartimento"),
                List.of(dipartimento.value),
                excludedList,
                null,
                false
        );
    }

    @Override
    public void insert(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        insertQuery("SECRETARY",List.of(secretary.getCodiceFiscale(),secretary.getDipartimento().value));
    }

    @Override
    public void delete(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException {
        deleteQuery("SECRETARY",List.of("codice_fiscale","dipartimento"), List.of(secretary.getCodiceFiscale(), secretary.getDipartimento().value));
    }

    @Override
    public void update(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        updateQuery("SECRETARY",List.of("dipartimento"),List.of(secretary.getDipartimento().value),List.of("codice_fiscale"),List.of(secretary.getCodiceFiscale()));
    }
    @Override
    protected Secretary queryObjectBuilder(ResultSet rs, List<Object> users) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, UnrecognizedRoleException, WrongListQueryIdentifierValue, ObjectNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        return new Secretary(
                (users != null) ? (User)users.get(0) : UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("codice_fiscale")),
                DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento"))
        );
    }
    @Override
    protected String setGetListQueryIdentifiersValue(Secretary secretary, int valueNumber) throws WrongListQueryIdentifierValue {
        if(valueNumber == 0){
            return secretary.getCodiceFiscale();
        } else throw new WrongListQueryIdentifierValue(ExceptionMessagesEnum.WRONG_LIST_QUERY_IDENTIFIER_VALUE.message);
    }
}



