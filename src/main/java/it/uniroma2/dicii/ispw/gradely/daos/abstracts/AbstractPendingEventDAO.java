package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.model.PendingEvent;

import java.util.UUID;


public abstract class AbstractPendingEventDAO {
    protected static AbstractPendingEventDAO instance;
    protected AbstractPendingEventDAO(){ //TODO implementare costruttore vero
    }

    public abstract PendingEvent getPendingEventById(UUID id);
}
