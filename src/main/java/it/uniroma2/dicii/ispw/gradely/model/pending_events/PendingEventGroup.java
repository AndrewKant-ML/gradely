package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.List;
import java.util.UUID;

public class PendingEventGroup extends PendingEvent{
    private List<User> users;

    public PendingEventGroup() {
    }
    public PendingEventGroup(List<User> users, PendingEventTypeEnum type, String message) {
        UUID id;
        do{
            id=UUID.randomUUID();
        }while(SessionManager.getInstance().checkUUID(id));

        super.id = id;
        this.users = users;
        super.type = type;
        super.message = message;
        super.notified = false;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
}
