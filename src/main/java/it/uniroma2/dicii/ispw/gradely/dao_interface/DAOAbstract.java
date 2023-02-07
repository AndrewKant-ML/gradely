package it.uniroma2.dicii.ispw.gradely.dao_interface;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class DAOAbstract<T>{
    abstract void insert(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(T t) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(T t) throws DAOException, PropertyException, ResourceNotFoundException;

    abstract void setQueryParameters(PreparedStatement stmt, T t) throws SQLException;

    void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<String> values) throws SQLException {
        for (int i=1;i <= identifiers.size();i++){
            stmt.setString(i, values.get(i-1));
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
                setQueryParameters(stmt, t);
                stmt.executeUpdate();
            }
        } catch (
                SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    protected void cancelQuery(String table, List<String> identifiers, List<String> values, T t) throws PropertyException, ResourceNotFoundException, DAOException {
        StringBuilder builder = new StringBuilder();
        if (identifiers.size()!=values.size())
            throw new DAOException("id and valori don't match "); //TODO implementare exception
        for (String s : identifiers){
            builder.append(s).append(" = ? and");
        }
        builder.delete(builder.length() - 4, builder.length()-1);
        String query = String.format("delete from %s where %s",table,builder);
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

    protected void updateQuery(String table, List<String> columns, List<String> updateValues, List<String> identifiers, List<String> identifiersValue, T t) throws DAOException, PropertyException, ResourceNotFoundException {
        if (columns.size()!=updateValues.size()||identifiers.size()!=identifiersValue.size())
            throw new DAOException("id and valori don't match "); //TODO implementare exception
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
                setQueryParameters(stmt, t);
                setQueryIdentifiers(stmt, identifiers, identifiersValue);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }


}
