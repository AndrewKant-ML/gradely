package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;

import java.util.List;
import java.util.UUID;


public interface PendingEventDAOInterface {

    public abstract PendingEvent getPendingEventById(UUID id) throws DAOException;

    public abstract List<PendingEvent> getAllPendingEvents(List<PendingEvent> list);
}
