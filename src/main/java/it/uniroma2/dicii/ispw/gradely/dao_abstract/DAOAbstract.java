package it.uniroma2.dicii.ispw.gradely.dao_abstract;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOAbstract<T, O>{
    abstract void insert(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void setInsertQueryParameters(PreparedStatement stmt, T t) throws SQLException;
    abstract void setUpdateQueryParameters(PreparedStatement stmt, T t) throws SQLException;
    abstract void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> values) throws SQLException;
    T getListQueryObjectBuilder(ResultSet rs) throws SQLException, DAOException, PropertyException, ResourceNotFoundException{
        throw new DAOException("non permitted method call");
    }
    T getQueryObjectBuilder(ResultSet rs, List<O> o) throws SQLException, DAOException, PropertyException, ResourceNotFoundException{
        throw new DAOException("non permitted method call");
    }
    String getListQueryIdentifierValues(T t, int valueNumber) throws DAOException {
        throw new DAOException("non permitted method call");
    }
    protected List<T> getListQuery(String table, List<String> identifiers, List<Object> values, List<T> ts) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException{
        String query = String.format("select * from %s where %s",  table, queryStringBuilder(identifiers, values));
        String finalQuery;
        if (ts.isEmpty()) {
            finalQuery = query;
        } else {
            finalQuery = getListQueryExclusions(query,identifiers,ts);
        }
        List<T> list = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            setQueryIdentifiers(stmt, identifiers, values);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.first()) {
                    list.add(getListQueryObjectBuilder(rs));
                }
            } catch (PropertyException | ResourceNotFoundException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        return list;
    }
    String getListQueryExclusions(String query, List <String> identifiers, List<T> ts) throws DAOException {
        StringBuilder newQuery = new StringBuilder();
        for (String i : identifiers){
            StringBuilder andBuilder = new StringBuilder();
            StringBuilder valuesBuilder = new StringBuilder();
            andBuilder.append(" and ").append(i).append(" not in (%s)");
            for (T t : ts)
                valuesBuilder.append(getListQueryIdentifierValues(t, identifiers.indexOf(i))).append(',');
            valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);
            newQuery.append(String.format(andBuilder.toString(),valuesBuilder));
        }
        return query.concat(String.format("%s",newQuery));
    }
    protected T getQuery(String table, List<String> parameters, List<String> identifiers, List<Object> values, List<O> o) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException{
        StringBuilder parametersBuilder = new StringBuilder();
        StringBuilder identifiersBuilder = new StringBuilder();
        if (identifiers.size()!=values.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        for (String s : parameters)
            parametersBuilder.append(s).append(',');
        for (String s : identifiers)
            identifiersBuilder.append(s).append(" = ? and");
        parametersBuilder.deleteCharAt(parametersBuilder.length()-1);
        identifiersBuilder.delete(identifiersBuilder.length() - 4, identifiersBuilder.length()-1);
        String query = String.format("select %s from %s where %s", parametersBuilder, table, identifiersBuilder);
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            setQueryIdentifiers(stmt, identifiers, values);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return getQueryObjectBuilder(rs, o);
                } else
                    throw new UserNotFoundException(ExceptionMessagesEnum.STUDENT_NOT_FOUND.message);
            } catch (PropertyException | ResourceNotFoundException e) {
                throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
    protected void insertQuery(String table, List<String> columns, T t) throws DAOException, PropertyException, ResourceNotFoundException {
        StringBuilder columnBuilder = new StringBuilder();
        StringBuilder questionBuilder = new StringBuilder();
        for (String s : columns){
            columnBuilder.append(s).append(',');
            questionBuilder.append("?,");
        }
        columnBuilder.deleteCharAt(columnBuilder.length() - 1);
        questionBuilder.deleteCharAt(questionBuilder.length() - 1);

        String query = String.format("insert into %s (%s) values (%s)",table,columnBuilder,questionBuilder);
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setInsertQueryParameters(stmt, t);
                stmt.executeUpdate();
            }
        } catch (
                SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    protected void cancelQuery(String table, List<String> identifiers, List<Object> values, T t) throws PropertyException, ResourceNotFoundException, DAOException {
        String query = String.format("delete from %s where %s",table,queryStringBuilder(identifiers, values));
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryIdentifiers(stmt, identifiers, values);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    private StringBuilder queryStringBuilder(List<String> identifiers, List<Object> values) throws DAOException {
        StringBuilder builder = new StringBuilder();
        if (identifiers.size()!=values.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        for (String s : identifiers)
            builder.append(s).append(" = ? and");
        builder.delete(builder.length() - 4, builder.length()-1);
        return builder;
    }

    protected void updateQuery(String table, List<String> columns, List<String> identifiers, List<Object> identifiersValue, T t) throws DAOException, PropertyException, ResourceNotFoundException {
        if (identifiers.size()!=identifiersValue.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        StringBuilder columnBuilder = new StringBuilder();
        StringBuilder identifierBuilder = new StringBuilder();
        for (String s : columns){
            columnBuilder.append(s).append(" = ?, ");
        }
        columnBuilder.delete(columnBuilder.length() - 2, columnBuilder.length()-1);
        for (String s : identifiers){
            identifierBuilder.append(s).append(" = ?, ");
        }
        columnBuilder.delete(columnBuilder.length() - 2, columnBuilder.length()-1);
        String query = String.format("update %s set %s where %s",table,columnBuilder,identifierBuilder);
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setUpdateQueryParameters(stmt, t);
                setQueryIdentifiers(stmt, identifiers, identifiersValue);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }


}
