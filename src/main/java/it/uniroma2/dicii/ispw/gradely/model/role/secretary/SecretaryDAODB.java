package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecretaryDAODB extends AbstractSecretaryDAO {

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
    public Secretary getSecretaryByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException {
        String query = "select * from SECRETARY S where S.codice_fiscale='%s';";
        query = String.format(query, user.getCodiceFiscale());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return new Secretary(
                            user,
                            DipartimentoEnum.getDipartimentoByValue(rs.getInt("dipartimento"))
                    );
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.SECRETARY_NOT_FOUND.message);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    /**
     * Gets all the Secretaries of a given dipartimento
     *
     * @param dipartimento  the dipartimento whose secretaries have to be found
     * @param secretaryList the secretaries already loaded in memory
     * @return a List of Secretary objects
     * @throws DAOException          thrown if errors occur while retrieving data from persistence layer
     * @throws UserNotFoundException thrown if the given User has not a Secretary role
     */
    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> secretaryList) throws DAOException, UserNotFoundException, MissingAuthorizationException, PropertyException, ResourceNotFoundException, ObjectNotFoundException {
        String baseQuery = "select * from SECRETARY S where S.dipartimento=%d";
        String query;
        if (secretaryList.isEmpty())
            query = String.format(baseQuery, dipartimento.value);
        else {
            StringBuilder builder = new StringBuilder();
            for (Secretary secretary : secretaryList)
                builder.append(secretary.getUser().getCodiceFiscale()).append(',');
            builder.deleteCharAt(builder.length() - 1);
            query = String.format(baseQuery.concat("and S.codice_fiscale not in (%s);"), dipartimento.value, builder);
        }
        query = String.format(query, dipartimento.value);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<Secretary> newSecretaries = new ArrayList<>();
                while (rs.next()) {
                    User user = UserLazyFactory.getInstance().getUserByCodiceFiscale(rs.getString("codice_fiscale"));
                    newSecretaries.add(user.getRole().castToSecretaryRole());
                }
                return newSecretaries;
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    @Override
    public void insert(Secretary secretary){

    }

    @Override
    public void cancel(Secretary secretary){

    }

    @Override
    public void update(Secretary secretary){

    }

    @Override
    public List<Secretary> refresh(List<Secretary> secretaries){
        return null;
    }
}
