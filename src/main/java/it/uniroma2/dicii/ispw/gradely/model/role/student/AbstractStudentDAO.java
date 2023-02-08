package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractStudentDAO extends DAODBAbstract<Student, User> {
    protected static AbstractStudentDAO instance;

    protected AbstractStudentDAO(){

    }

    public abstract Student getStudentByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException;

    abstract void insert(Student student) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void cancel(Student student) throws DAOException, PropertyException, ResourceNotFoundException;
    abstract void update(Student student) throws DAOException, PropertyException, ResourceNotFoundException;

    abstract Student getQueryObjectBuilder(ResultSet rs, List<User> user) throws SQLException, DAOException, PropertyException, ResourceNotFoundException;
    abstract void setInsertQueryParametersValue(PreparedStatement stmt, Student student) throws SQLException;
    abstract void setUpdateQueryParametersValue(PreparedStatement stmt, Student student) throws SQLException;
    abstract void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> values) throws SQLException;


}
