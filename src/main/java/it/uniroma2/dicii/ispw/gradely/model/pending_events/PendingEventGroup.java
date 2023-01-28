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
        UUID generatedId;
        do{
            generatedId=UUID.randomUUID();
        }while(SessionManager.getInstance().checkUUID(generatedId));

        super.id = generatedId;
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
