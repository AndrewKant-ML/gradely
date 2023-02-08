package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PendingEventLazyFactory {
    private static PendingEventLazyFactory instance;
    private final List<PendingEvent> pendingEvents;

    private PendingEventLazyFactory() throws DAOException{
        pendingEvents = new ArrayList<PendingEvent>();
        refreshPendingEvents();
    }

    public static synchronized PendingEventLazyFactory getInstance() throws DAOException{
        if (instance == null){
            instance = new PendingEventLazyFactory();
        }
        return instance;
    }

    public List<PendingEvent> getPendingEventsByUser(User user) throws DAOException {
        refreshPendingEvents();
        List<PendingEvent> list = new ArrayList<>();
        for (PendingEvent p : pendingEvents){
            if (Boolean.TRUE.equals(p.isForUser(user))){
                list.add(p);
            }
        }
        return list;
    }

    public void createNewPendingEvent(List<String> recipients, PendingEventTypeEnum type, Boolean notified, Object object) throws DAOException {
        PendingEvent p = new PendingEvent(recipients, type, notified, object);
        try {
            DAOFactoryAbstract.getInstance().getPendingEventDAO().insert(p);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        pendingEvents.add(p);
    }

    private void refreshPendingEvents() throws DAOException {
        try {
            this.pendingEvents.addAll(DAOFactoryAbstract.getInstance().getPendingEventDAO().getAllPendingEvents(pendingEvents));
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public Boolean checkUUID(UUID id) throws DAOException {
        refreshPendingEvents();
        for(PendingEvent event : pendingEvents){
            if(id.equals(event.getId())){
                return false;
            }
        }
        return true;
    }


}
