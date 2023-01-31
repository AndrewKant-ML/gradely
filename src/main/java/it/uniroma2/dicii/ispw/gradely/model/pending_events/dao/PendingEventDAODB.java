package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;

import java.util.List;
import java.util.UUID;


public class PendingEventDAODB extends AbstractPendingEventDAO {

    private PendingEventDAODB(){ //TODO implementare costruttore vero

    }

    public static AbstractPendingEventDAO getInstance(){
        if (instance == null) {
            instance = new PendingEventDAODB();
        }
        return instance;
    }

    @Override
    public PendingEvent getPendingEventById(UUID id) {
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(PendingEvent pendingEvent) {

    }

    @Override
    public void cancel(PendingEvent pendingEvent) {

    }

    @Override
    public void update(PendingEvent pendingEvent){
        System.out.println("Updated");
    }

    @Override
    public List<PendingEvent> refresh(List<PendingEvent> pendingEvents) {
        return null;
    }

}
