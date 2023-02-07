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
    private final List<AbstractPendingEvent> abstractPendingEvents;

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
        try {
            return DAOFactoryAbstract.getInstance().getPendingEventDAO().getPendingEventById(id);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public void createNewPendingEventSingle(String codiceFiscale, PendingEventTypeEnum type, Object object) throws DAOException {
        AbstractPendingEvent p = new PendingEventSingle(codiceFiscale, type, object);
        abstractPendingEvents.add(p);
        try {
            DAOFactoryAbstract.getInstance().getPendingEventDAO().update(p);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

    public void createNewPendingEventGroup(List<String> codiceFiscales, PendingEventTypeEnum type, Object object) throws DAOException {
        PendingEventGroup p = new PendingEventGroup(codiceFiscales, type, object);
        abstractPendingEvents.add(p);
        try {
            DAOFactoryAbstract.getInstance().getPendingEventDAO().update(p);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }

}
