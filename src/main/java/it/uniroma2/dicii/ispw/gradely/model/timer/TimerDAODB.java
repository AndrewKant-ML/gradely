package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.dao_manager.DBConnection;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;

public class TimerDAODB extends DAODBAbstract<AbstractTimer> implements TimerDAOInterface {
    protected static TimerDAOInterface instance;

    private TimerDAODB(){
        super();
    }

    public static synchronized TimerDAOInterface getInstance(){
        if (instance == null){
            instance = new TimerDAODB();
        }
        return instance;
    }


    @Override
    public void insert(AbstractTimer timer){

        String query = "insert into TIMER (codice_fiscale, name, surname, password, registration_date, email, role) values (?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                setQueryParameters(stmt, user);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message,e);
        }
    }

    @Override
    public void cancel(AbstractTimer timer){

    }

    @Override
    public void update(AbstractTimer timer){

    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, AbstractTimer timer) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, AbstractTimer timer) throws SQLException, MissingAuthorizationException {

    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }


}
