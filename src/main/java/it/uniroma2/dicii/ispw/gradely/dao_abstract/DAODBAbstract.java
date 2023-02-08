package it.uniroma2.dicii.ispw.gradely.dao_abstract;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAODBAbstract<T, O>{
    /**
     * Inserts an object into the DB
     * @param t the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void insert(T t) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Deletes an object from the DB
     * @param t the object to cancel
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void cancel(T t) throws DAOException, PropertyException, ResourceNotFoundException;

    protected abstract void cancel(User user) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in the DB to its current state
     * @param t the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void update(T t) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Sets the insert parameters value into a sql prepared statement getting the values from an object
     * @param stmt the statement
     * @param t the object where to take the values from
     * @throws SQLException thrown if an error occurs with the DB
     */
    protected abstract void setInsertQueryParametersValue(PreparedStatement stmt, T t) throws SQLException;


    /**
     * Sets update parameters value into a sql prepared statement getting the values from an object
     * @param stmt the statement
     * @param t the object where to take the values from
     * @throws SQLException thrown if an error occurs with the DB
     */
    protected abstract void setUpdateQueryParametersValue(PreparedStatement stmt, T t) throws SQLException;

    /**
     * Set identifiers into a sql prepared statement
     * @param stmt the statement
     * @param identifiers the identifiers name to set in the query
     * @param identifiersValues the value of such identifiers
     * @throws SQLException thrown if an error occurs with the DB
     */
    protected abstract void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException;

    /**
     * Instantiate the specific objects of a list query
     * @param rs the result set where to take the information from
     * @return a list of objects
     * @throws SQLException thrown if an error occurs with the DB
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected T getListQueryObjectBuilder(ResultSet rs) throws SQLException, DAOException, PropertyException, ResourceNotFoundException{
        throw new DAOException("non permitted method call");
    }

    /**
     * Instantiates the specific object of a query
     * using a list of objects needed for its construction
     * @param rs the result set where to take the information from
     * @param o the list of objects needed for the instatiation
     * @return an instance of the requested object
     * @throws SQLException thrown if an error occurs with the DB
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected T getQueryObjectBuilder(ResultSet rs, List<Object> o) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException {
        throw new DAOException("non permitted method call");
    }

    /**
     * Gets the value of one of the identifier for an element
     * of the excluded list of a list query
     * @param t the element to be excluded
     * @param valueNumber the index of the current value in the identifiers list
     * @return the string to be inserted into the query in order to exclude the element
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    protected String getListQueryIdentifierValue(T t, int valueNumber) throws DAOException {
        throw new DAOException("non permitted method call");
    }

    /**
     * Queries the DB for a list of objects, excluding all the entries
     * correlated to a given list, to avoid redundant reads from the DB
     *
     * @param table the table where to find the information
     * @param identifiers the name of the columns needed to find the entry in the table
     * @param values the value of such identifiers
     * @param ts the list of objects to be excluded from the query
     * @return a list of objects of the requested type
     * @throws UserNotFoundException
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected protected List<T> getListQuery(String table, List<String> identifiers, List<Object> values, List<T> ts) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException{
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

    /**
     * Concatenates to a query string the string needed to exclude the elements
     * present in a list, such as the elements already present in memory
     * @param query the original query where to concatenate the exclusion part
     * @param identifiers the identifier columns needed to identify the elements to exclude
     * @param ts the list of objects to exclude
     * @return the new query string
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    protected String getListQueryExclusions(String query, List <String> identifiers, List<T> ts) throws DAOException {
        StringBuilder newQuery = new StringBuilder();
        for (String i : identifiers){
            StringBuilder andBuilder = new StringBuilder();
            StringBuilder valuesBuilder = new StringBuilder();
            andBuilder.append(" and ").append(i).append(" not in (%s)");
            for (T t : ts)
                valuesBuilder.append(getListQueryIdentifierValue(t, identifiers.indexOf(i))).append(',');
            valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);
            newQuery.append(String.format(andBuilder.toString(),valuesBuilder));
        }
        return query.concat(String.format("%s",newQuery));
    }

    /**
     * Queries the DB to get the information needed to instantiate an object
     *
     * @param table the table where to find the information
     * @param parameters the parameter columns where the information is stored
     * @param identifiers the name of the columns needed to find the entry in the table
     * @param identifiersValues the value of such identifiers
     * @param o the objects needed to instantiate the new object (e.g. to make a student, a user is needed)
     * @return an instance of the requested object
     * @throws UserNotFoundException
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected T getQuery(String table, List<String> parameters, List<String> identifiers, List<Object> identifiersValues, List<O> o) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException{
        StringBuilder parametersBuilder = new StringBuilder();
        StringBuilder identifiersBuilder = new StringBuilder();
        if (identifiers.size()!=identifiersValues.size())
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
            setQueryIdentifiers(stmt, identifiers, identifiersValues);
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

    /**
     * Queries DB to insert an entry into a table
     *
     * @param table the table to insert into
     * @param columns the columns necessary to insert
     * @param t the object containing the information
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
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
                setInsertQueryParametersValue(stmt, t);
                stmt.executeUpdate();
            }
        } catch (
                SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    /**
     * Queries DB to cancel the entry of a table related to an object
     *
     * @param table the table to cancel from
     * @param identifiers the name of the columns needed to find the entry in the table
     * @param identifiersValues the identifiers value needed to find the entry to update
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    protected void cancelQuery(String table, List<String> identifiers, List<Object> identifiersValues) throws PropertyException, ResourceNotFoundException, DAOException {
        String query = String.format("delete from %s where %s",table,queryStringBuilder(identifiers, identifiersValues));
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryIdentifiers(stmt, identifiers, identifiersValues);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    /**
     * Builds a query string from a list of column names and the list of their value
     * @param identifiers the identifiers name list
     * @param values the values of such identifiers
     * @return the Builded String
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    private StringBuilder queryStringBuilder(List<String> identifiers, List<Object> values) throws DAOException {
        StringBuilder builder = new StringBuilder();
        if (identifiers.size()!=values.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        for (String s : identifiers)
            builder.append(s).append(" = ? and");
        builder.delete(builder.length() - 4, builder.length()-1);
        return builder;
    }

    /**
     * Queries DB to update the entry of a table related to an object
     * with fresh information provided by the actual state of the object
     * itself
     *
     * @param table the table to update
     * @param columns the columns to be updated
     * @param identifiers the identifiers columns aka the primary key
     * @param identifiersValue the identifiers value needed to find the entry to update
     * @param t the object which state will be updated in the DB
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
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
                setUpdateQueryParametersValue(stmt, t);
                setQueryIdentifiers(stmt, identifiers, identifiersValue);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }


}
