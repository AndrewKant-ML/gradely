package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PendingEventDAOFS implements PendingEventDAOInterface {

    private static PendingEventDAOFS instance;

    private PendingEventDAOFS(){ 

    }

    public static synchronized PendingEventDAOInterface getInstance(){
        if (instance == null){
            instance = new PendingEventDAOFS();
        }
        return instance;
    }

    public PendingEvent getPendingEventById(UUID id) throws DAOException {
        return null; 
    }

    @Override
    public List<PendingEvent> getAllPendingEvents(List<PendingEvent> list) {
        return new ArrayList<>();
    }

    @Override
    public void insert(PendingEvent pendingEvent){
        // To be implemented
    }

    @Override
    public void delete(PendingEvent pendingEvent){
        // To be implemented
    }

    @Override
    public void update(PendingEvent pendingEvent){
        // To be implemented
    }

}
