package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;

import java.util.List;
import java.util.UUID;


public class PendingEventDAOFS extends AbstractPendingEventDAO {

    private PendingEventDAOFS(){ 

    }

    public static synchronized AbstractPendingEventDAO getInstance(){
        if (instance == null){
            instance = new PendingEventDAOFS();
        }
        return instance;
    }

    @Override
    public PendingEvent getPendingEventById(UUID id) throws DAOException {
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

    @Override
    public List<PendingEvent> refresh(List<PendingEvent> pendingEvents){
        return null;
    }
}
