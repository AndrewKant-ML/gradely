package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.AbstractPendingEvent;

import java.util.UUID;


public abstract class AbstractPendingEventDAO implements DAOInterface<AbstractPendingEvent> {
    protected static AbstractPendingEventDAO instance;

    protected AbstractPendingEventDAO(){
    }

    public abstract AbstractPendingEvent getPendingEventById(UUID id) throws DAOException;

}
