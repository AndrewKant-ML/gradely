package it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event;

import it.uniroma2.dicii.ispw.gradely.beans_general.PendingEventBean;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.pending_events.PendingEventLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class PendingEventController {

    public List<PendingEventBean> retrievePendingEvents(String tokenKey) throws DAOException {
        User user = SessionManager.getInstance().getSessionUserByTokenKey(tokenKey);
        List<PendingEvent> pendingEvents = PendingEventLazyFactory.getInstance().getPendingEventsByUser(user);
        List<PendingEventBean> beans = new ArrayList<>();
        for (PendingEvent pendingEvent : pendingEvents) {
            beans.add(new PendingEventBean(
                    pendingEvent.getId(),
                    pendingEvent.getType(),
                    pendingEvent.getNotified()
            ));
        }
        return beans;
    }
}
