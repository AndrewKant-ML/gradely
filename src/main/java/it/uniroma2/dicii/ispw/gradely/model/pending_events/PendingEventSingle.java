package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class PendingEventSingle extends AbstractPendingEvent {
    private User user; // TODO collapse User to codice fiscale

    public PendingEventSingle(User user, PendingEventTypeEnum type, Object object) throws DAOException {
        super(type, object);
        this.user = user;
        this.notified = false;
    }

    @Override
    public Boolean isForUser(User user) {
        return (this.user == user);
    }
    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

}
