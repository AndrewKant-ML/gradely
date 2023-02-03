package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PendingEventLazyFactory {
    private static PendingEventLazyFactory instance;
    private List<AbstractPendingEvent> abstractPendingEvents;

    private PendingEventLazyFactory(){
        abstractPendingEvents = new ArrayList<AbstractPendingEvent>();
    }

    public static synchronized PendingEventLazyFactory getInstance(){
        if (instance == null){
            instance = new PendingEventLazyFactory();
        }
        return instance;
    }

    public AbstractPendingEvent getPendingEventById(UUID id) throws DAOException {
        for(AbstractPendingEvent p : abstractPendingEvents){
            if(p.getId().equals(id)){
                return p; 
            }
        }
        return DAOFactoryAbstract.getInstance().getPendingEventDAO().getPendingEventById(id);
    }

    public void createNewPendingEventSingle(User user, PendingEventTypeEnum type, Object object) throws DAOException {
        AbstractPendingEvent p = new PendingEventSingle(user, type, object);
        abstractPendingEvents.add(p);
        DAOFactoryAbstract.getInstance().getPendingEventDAO().update(p);
    }

    public void createNewPendingEventGroup(List<User> users, PendingEventTypeEnum type, Object object) throws DAOException {
        PendingEventGroup p = new PendingEventGroup(users, type, object);
        abstractPendingEvents.add(p);
        DAOFactoryAbstract.getInstance().getPendingEventDAO().update(p);
    }

}
