package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractSecretaryDAO extends DAOAbstract<Secretary, User> {
    protected static AbstractSecretaryDAO instance;

    protected AbstractSecretaryDAO(){
    }

    public abstract Secretary getSecretaryByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException;
    public abstract List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> secretaryList) throws DAOException, UserNotFoundException, MissingAuthorizationException, PropertyException, ResourceNotFoundException, ObjectNotFoundException;
    abstract void insert(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(Secretary secretary) throws DAOException, PropertyException, ResourceNotFoundException;

    abstract Secretary getQueryObjectBuilder(ResultSet rs, List<User> user) throws SQLException, DAOException, PropertyException, ResourceNotFoundException;
    abstract void setInsertQueryParameters(PreparedStatement stmt, Secretary secretary) throws SQLException;
    abstract void setUpdateQueryParameters(PreparedStatement stmt, Secretary secretary) throws SQLException;
    abstract void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> values) throws SQLException;
    abstract Secretary getListQueryObjectBuilder(ResultSet rs) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UserNotFoundException, MissingAuthorizationException;
    abstract String getListQueryIdentifierValues(Secretary secretary, int valueNumber) throws Exception;

}
