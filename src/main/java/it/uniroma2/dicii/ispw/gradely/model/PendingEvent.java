package it.uniroma2.dicii.ispw.gradely.model;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.UUID;

public class PendingEvent {
    private UUID ID;
    private User user;
    private PendingEventTypeEnum type;
    private String message;
    private Boolean notified;

    public PendingEvent() {
    }
    public PendingEvent(User user, PendingEventTypeEnum type, String message) {
        UUID ID;
        do{
            ID=UUID.randomUUID();
        }while(SessionManager.getInstance().getSession(user).checkUUID(ID));

        this.ID = ID;
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

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
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
}
