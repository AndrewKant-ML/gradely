package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.UUID;

public class PendingEventSingle extends PendingEvent{
    private User user;

    public PendingEventSingle() {
    }
    public PendingEventSingle(User user, PendingEventTypeEnum type, String message) {
        UUID generatedId;
        do{
            generatedId=UUID.randomUUID();
        }while(SessionManager.getInstance().checkUUID(generatedId));

        this.id = generatedId;
        this.user = user;
        this.type = type;
        this.message = message;
        this.notified = false;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public PendingEventTypeEnum getType() {
        return type;
    }

    @Override
    public void setType(PendingEventTypeEnum type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Boolean getNotified() {
        return notified;
    }

    @Override
    public void setNotified(Boolean notified) {
        this.notified = notified;
    }
}
