package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractPendingEvent {
    protected UUID id;
    protected PendingEventTypeEnum type;
    protected Boolean notified;
    protected Object object;

    protected AbstractPendingEvent(PendingEventTypeEnum type, Object object) throws DAOException {
        UUID generatedId;
        do {
            generatedId = UUID.randomUUID();
        } while (!Boolean.TRUE.equals(SessionManager.getInstance().checkUUID(generatedId)));

        this.id = generatedId;
        this.type = type;
        this.notified = false;
        this.object = object;
    }

    public abstract Boolean isForUser(User user);

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public PendingEventTypeEnum getType(){
        return type;
    }

    public void setType(PendingEventTypeEnum type){
        this.type = type;
    }

    public Boolean getNotified(){
        return notified;
    }

    public void setNotified(Boolean notified){
        this.notified = notified;
    }

    public Object getObject(){
        return object;
    }

    public void setObject(Objects object){
        this.object = object;
    }
}
