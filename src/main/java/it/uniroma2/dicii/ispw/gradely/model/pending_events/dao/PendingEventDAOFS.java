package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.AbstractPendingEvent;

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
    public AbstractPendingEvent getPendingEventById(UUID id) throws DAOException {
        return null; 
    }

    @Override
    public void insert(AbstractPendingEvent abstractPendingEvent){

    }

    @Override
    public void cancel(AbstractPendingEvent abstractPendingEvent){

    }

    @Override
    public void update(AbstractPendingEvent abstractPendingEvent){
        System.out.println("Updated");
    }

    @Override
    public List<AbstractPendingEvent> refresh(List<AbstractPendingEvent> abstractPendingEvents){
        return null;
    }
}
