package it.uniroma2.dicii.ispw.gradely.model.pending_events.dao;

import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;

import java.util.List;
import java.util.UUID;


public abstract class AbstractPendingEventDAO {
    protected static AbstractPendingEventDAO instance;
    protected List<PendingEvent> pendingEvents;

    protected AbstractPendingEventDAO(){
    }

    public abstract PendingEvent getPendingEventById(UUID id);

    public abstract void update(PendingEvent pendingEvent);

    public abstract List<PendingEvent> refresh(List<PendingEvent> pendingEvents);


}
