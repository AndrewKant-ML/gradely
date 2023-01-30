package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventGroup;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PendingEventLazyFactory {
    private static PendingEventLazyFactory instance;
    private List<PendingEvent> pendingEvents;

    private PendingEventLazyFactory(){
        pendingEvents = new ArrayList<PendingEvent>();
    }

    public static PendingEventLazyFactory getInstance(){
        if (instance == null) {
            instance = new PendingEventLazyFactory();
        }
        return instance;
    }

    public PendingEvent getPendingEventById(UUID id) {
        for(PendingEvent p : pendingEvents){
            if(p.getId().equals(id)) {
                return p; //TODO implementare exception
            }
        }
        return DAOFactory.getDAOFactory().getPendingEventDAO().getPendingEventById(id); //TODO implementare exception
    }

    public void createNewPendingEventSingle(User user, PendingEventTypeEnum type, Object object){
        PendingEvent p = new PendingEventSingle(user, type, object);
        pendingEvents.add(p);
        DAOFactory.getDAOFactory().getPendingEventDAO().update(p);
    }

    public void createNewPendingEventGroup(List<User> users, PendingEventTypeEnum type, Object object){
        PendingEventGroup p = new PendingEventGroup(users, type, object);
        pendingEvents.add(p);
        DAOFactory.getDAOFactory().getPendingEventDAO().update(p);
    }

}
