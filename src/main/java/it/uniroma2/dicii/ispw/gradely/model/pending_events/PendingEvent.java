package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.Objects;
import java.util.UUID;

public abstract class PendingEvent {
    protected UUID id;
    protected PendingEventTypeEnum type;
    protected String message;
    protected Boolean notified;
    protected Objects object;

    protected PendingEvent() {
    }
    protected PendingEvent(User user, PendingEventTypeEnum type, String message, Objects object) {
        UUID id;
        do{
            id=UUID.randomUUID();
        }while(SessionManager.getInstance().checkUUID(id));

        this.id = id;
        this.type = type;
        this.message = message;
        this.notified = false;
        this.object = object;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PendingEventTypeEnum getType() {
        return type;
    }

    public void setType(PendingEventTypeEnum type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    public Objects getObject() {
        return object;
    }

    public void setObject(Objects object) {
        this.object = object;
    }
}
