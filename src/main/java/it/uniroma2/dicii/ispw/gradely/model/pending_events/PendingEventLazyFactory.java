package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

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
        return DAOFactoryAbstract.getDAOFactory().getPendingEventDAO().getPendingEventById(id); //TODO implementare exception
    }

    public void createNewPendingEventSingle(User user, PendingEventTypeEnum type, Object object){
        PendingEvent p = new PendingEventSingle(user, type, object);
        pendingEvents.add(p);
        DAOFactoryAbstract.getDAOFactory().getPendingEventDAO().update(p);
    }

    public void createNewPendingEventGroup(List<User> users, PendingEventTypeEnum type, Object object){
        PendingEventGroup p = new PendingEventGroup(users, type, object);
        pendingEvents.add(p);
        DAOFactoryAbstract.getDAOFactory().getPendingEventDAO().update(p);
    }

}
