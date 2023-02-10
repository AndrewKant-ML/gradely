package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class PendingEventDAODB extends DAODBAbstract<PendingEvent> implements PendingEventDAOInterface {
    protected static PendingEventDAOInterface instance;

    private PendingEventDAODB(){ 

    }

    public static synchronized PendingEventDAOInterface getInstance(){
        if (instance == null){
            instance = new PendingEventDAODB();
        }
        return instance;
    }

    @Override
    public PendingEvent getPendingEventById(UUID id) throws DAOException {
        return null; 
    }

    @Override
    public List<PendingEvent> getAllPendingEvents(List<PendingEvent> list) {
        return null;
    }

    @Override
    public void insert(PendingEvent pendingEvent){
        insertQuery("PENDING_EVENT");
    }

    @Override
    public void cancel(PendingEvent pendingEvent){

    }

    @Override
    public void update(PendingEvent pendingEvent){
        System.out.println("Updated");
    }

    @Override
    protected void setInsertQueryParametersValue(PreparedStatement stmt, PendingEvent pendingEvent) throws SQLException {

    }

    @Override
    protected void setUpdateQueryParametersValue(PreparedStatement stmt, PendingEvent pendingEvent) throws SQLException, MissingAuthorizationException {

    }



    @Override
    protected String setGetListQueryIdentifiersValue(PendingEvent pendingEvent, int valueNumber) throws DAOException {
        return null;
    }

    @Override
    protected void setQueryIdentifiers(PreparedStatement stmt, List<String> identifiers, List<Object> identifiersValues) throws SQLException {

    }


}
