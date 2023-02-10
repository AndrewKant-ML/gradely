package it.uniroma2.dicii.ispw.gradely.dao_abstract;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAODBAbstract<T>{
    /**
     * Inserts an object into DB
     * @param t the object to insert
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void insert(T t) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Deletes an object from DB
     * @param t the object to cancel
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void cancel(T t) throws DAOException, PropertyException, ResourceNotFoundException;

    /**
     * Updates an object present in DB to its current state
     * @param t the object to be updated
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract void update(T t) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException;

    /**
     * Instantiates the specific object of a query using a list of the objects if needed for its construction.
     * @param rs the result set where to take the information from
     * @param objects the list of objects that could be needed for the instantiation
     * @return an instance of the requested object
     * @throws SQLException thrown if an error occurs with the DB
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected abstract T queryObjectBuilder(ResultSet rs, List<Object> objects) throws SQLException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, UserNotFoundException, MissingAuthorizationException, WrongDegreeCourseCodeException;

    /**
     * Gets the value of one of the identifiers for an element of the excluded list of a list query
     * @param t the element to be excluded
     * @param valueNumber the index of the current value in the identifiers list
     * @return the string to be inserted into the query in order to exclude the element
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    protected abstract String setGetListQueryIdentifiersValue(T t, int valueNumber) throws DAOException, WrongListQueryIdentifierValue;

    /**
     * Set values onto sql prepared statement question marks
     *
     * @param stmt              the statement
     * @param values the value of such identifiers
     * @throws SQLException thrown if an error occurs with the DB
     */
    protected void setQueryQuestionMarksValue(PreparedStatement stmt, List<Object> values, Integer start) throws SQLException{
        for(Object o : values)
            stmt.setObject(values.indexOf(o)+start,o);
    }

    /**
     * Queries the DB for a list of objects, excluding all the entries
     * correlated to a given list, to avoid redundant reads from the DB
     *
     * Helper methods: getListQueryExclusions, setQueryIdentifiers, getListQueryObjectBuilder
     *
     * @param table the table where to find the information
     * @param identifiers the name of the columns needed to find the entry in the table
     * @param identifiersValue the value of such identifiers
     * @param exclusions the list of objects to be excluded from the query
     * @return a list of objects of the requested type
     * @throws UserNotFoundException
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected List<T> getListQuery(String table, List<String> identifiers, List<Object> identifiersValue, List<T> exclusions, List<Object> objects) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, UnrecognizedRoleException, WrongListQueryIdentifierValue, WrongDegreeCourseCodeException {
        String query = String.format("select * from %s where %s",table, andStringBuilder(identifiers, identifiersValue));
        String finalQuery;
        if (exclusions.isEmpty()) {
            finalQuery = query;
        } else {
            finalQuery = getListQueryExclusions(query,identifiers,exclusions);
        }
        List<T> list = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(finalQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            setQueryQuestionMarksValue(stmt, identifiersValue,1);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(queryObjectBuilder(rs, objects));
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
    protected String getListQueryExclusions(String query, List <String> identifiers, List<T> ts) throws DAOException, WrongListQueryIdentifierValue {
        StringBuilder newQuery = new StringBuilder();
        for (String i : identifiers){
            StringBuilder andBuilder = new StringBuilder();
            StringBuilder valuesBuilder = new StringBuilder();
            andBuilder.append(" and ").append(i).append(" not in (%s)");
            for (T t : ts)
                valuesBuilder.append('\'').append(setGetListQueryIdentifiersValue(t, identifiers.indexOf(i))).append('\'').append(',');
            valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);
            newQuery.append(String.format(andBuilder.toString(),valuesBuilder));
        }
        return query.concat(newQuery.toString());
    }

    /**
     * Queries the DB to get the information needed to instantiate an object
     *
     * @param table the table where to find the information
     * @param identifiers the name of the columns needed to find the entry in the table
     * @param identifiersValues the value of such identifiers
     * @param objects the objects needed to instantiate the new object (e.g. to make a student, a user is needed)
     * @return an instance of the requested object
     * @throws UserNotFoundException
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected T getQuery(String table, List<String> identifiers, List<Object> identifiersValues, List<Object> objects) throws UserNotFoundException, DAOException, PropertyException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, ObjectNotFoundException, WrongDegreeCourseCodeException {
        String query = String.format("select * from %s where %s", table, andStringBuilder(identifiers,identifiersValues));
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            setQueryQuestionMarksValue(stmt, identifiersValues,1);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.first()) {
                    return queryObjectBuilder(rs, objects);
                } else
                    throw new ObjectNotFoundException(ExceptionMessagesEnum.OBJ_NOT_FOUND.message);
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
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected void insertQuery(String table, List<Object> parametersValue) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        StringBuilder questionBuilder = new StringBuilder();
        for (Object ignored : parametersValue){
            questionBuilder.append("?,");
        }
        questionBuilder.deleteCharAt(questionBuilder.length() - 1);

        String query = String.format("insert into %s values (%s)",table,questionBuilder);
        setQuestionMarksAndExecuteQuery(parametersValue, query);
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
        String query = String.format("delete from %s where %s",table, andStringBuilder(identifiers, identifiersValues));
        setQuestionMarksAndExecuteQuery(identifiersValues, query);
    }

    /**
     * Sets the query question marks to provided values and executes the query
     * @param values the values for the question marks
     * @param query the query to be filled and executed
     * @throws ResourceNotFoundException
     * @throws PropertyException
     * @throws DAOException
     */
    private void setQuestionMarksAndExecuteQuery(List<Object> values, String query) throws ResourceNotFoundException, PropertyException, DAOException {
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryQuestionMarksValue(stmt, values,1);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    /**
     * Queries DB to update the entry of a table related to an object
     * with fresh information provided by the actual state of the object
     * itself
     *
     * @param table the table to update
     * @param parameters the columns to be updated
     * @param parametersValues the values to update
     * @param identifiers the identifiers columns aka the primary key
     * @param identifiersValue the identifiers value needed to find the entry to update
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     * @throws PropertyException thrown if errors occur while loading properties from .properties file
     * @throws ResourceNotFoundException thrown if the properties resource file cannot be found
     */
    protected void updateQuery(String table, List<String> parameters, List<Object> parametersValues, List<String> identifiers, List<Object> identifiersValue) throws DAOException, PropertyException, ResourceNotFoundException, MissingAuthorizationException {
        if (identifiers.size()!=identifiersValue.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        StringBuilder columnBuilder = commaStringBuilder(parameters,parametersValues);
        StringBuilder identifierBuilder = andStringBuilder(identifiers,identifiersValue);
        String query = String.format("update %s set %s where %s",table,columnBuilder,identifierBuilder);
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryQuestionMarksValue(stmt, parametersValues,1);
                setQueryQuestionMarksValue(stmt, identifiersValue,parametersValues.size()+1);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    /**
     * Builds a comma separated, column names and question marks query string piece from a list of names and the list of their value
     * @param names the identifiers name list
     * @param values the values of such identifiers
     * @return the built String
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    private StringBuilder commaStringBuilder(List<String> names, List<Object> values) throws DAOException {
        StringBuilder builder = new StringBuilder();
        if (names.size()!=values.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        for (String s : names)
            builder.append(s).append(" = ? ,");
        builder.deleteCharAt(builder.length()-1);
        return builder;
    }

    /**
     * Builds an and separated, column names and question marks query string piece from a list of names and the list of their value
     * @param names the identifiers name list
     * @param values the values of such identifiers
     * @return the built String
     * @throws DAOException thrown if errors occur while retrieving data from persistence layer
     */
    private StringBuilder andStringBuilder(List<String> names, List<Object> values) throws DAOException {
        StringBuilder builder = new StringBuilder();
        if (names.size()!=values.size())
            throw new DAOException("id and values number don't match "); //TODO implementare exception
        for (String s : names)
            builder.append(s).append(" = ? and");
        builder.delete(builder.length() - 4, builder.length()-1);
        return builder;
    }
}
