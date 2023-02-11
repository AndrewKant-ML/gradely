package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PendingEventLazyFactory {
    private static PendingEventLazyFactory instance;
    private final List<PendingEvent> pendingEvents;

    private PendingEventLazyFactory() throws DAOException{
        pendingEvents = new ArrayList<>();
    }

    public static synchronized PendingEventLazyFactory getInstance() throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        if (instance == null){
            instance = new PendingEventLazyFactory();
        }
        instance.refreshPendingEvents();
        return instance;
    }

    public List<PendingEvent> getPendingEventsByUser(User user) throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        refreshPendingEvents();
        List<PendingEvent> list = new ArrayList<>();
        for (PendingEvent p : pendingEvents){
            if (Boolean.TRUE.equals(p.isForUser(user))){
                list.add(p);
            }
        }
        return list;
    }
    public void createNewPendingEvent(List<String> recipients, PendingEventTypeEnum type, Object object) throws DAOException, MissingAuthorizationException {
        PendingEvent p = new PendingEvent(recipients, type, object);
        try {
            DAOFactoryAbstract.getInstance().getPendingEventDAO().insert(p);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        pendingEvents.add(p);
    }

    public void createExistingPendingEvent(UUID id, List<String> recipients, PendingEventTypeEnum type, Boolean notified, Object object) throws DAOException, MissingAuthorizationException {
        PendingEvent p = new PendingEvent(id, recipients, type, notified, object);
        try {
            DAOFactoryAbstract.getInstance().getPendingEventDAO().insert(p);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        pendingEvents.add(p);
    }

    private void refreshPendingEvents() throws DAOException, UserNotFoundException, WrongListQueryIdentifierValue, ObjectNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        try {
            this.pendingEvents.addAll(DAOFactoryAbstract.getInstance().getPendingEventDAO().getAllPendingEvents(pendingEvents));
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }
}
