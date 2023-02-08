package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;

import java.util.List;
import java.util.UUID;


public abstract class AbstractPendingEventDAO implements DAOAbstract<PendingEvent> {
    protected static AbstractPendingEventDAO instance;

    protected AbstractPendingEventDAO(){
    }

    public abstract PendingEvent getPendingEventById(UUID id) throws DAOException;

    public abstract List<PendingEvent> getAllPendingEvents(List<PendingEvent> list);
}
