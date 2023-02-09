package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;

import java.util.UUID;


public class PendingEventDAOFS extends PendingEventDAOInterface {

    private PendingEventDAOFS(){ 

    }

    public static synchronized PendingEventDAOInterface getInstance(){
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

}
