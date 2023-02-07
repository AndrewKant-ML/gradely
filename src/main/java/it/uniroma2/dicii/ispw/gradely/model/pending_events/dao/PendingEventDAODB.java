package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;

import java.util.List;
import java.util.UUID;


public class PendingEventDAODB extends AbstractPendingEventDAO {

    private PendingEventDAODB(){ 

    }

    public static synchronized AbstractPendingEventDAO getInstance(){
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

    }

    @Override
    public void cancel(PendingEvent pendingEvent){

    }

    @Override
    public void update(PendingEvent pendingEvent){
        System.out.println("Updated");
    }


}
