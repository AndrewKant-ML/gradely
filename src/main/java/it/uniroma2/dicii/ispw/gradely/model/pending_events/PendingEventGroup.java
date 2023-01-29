package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PendingEventGroup extends PendingEvent{
    private List<User> users;

    public PendingEventGroup() {
    }
    public PendingEventGroup(List<User> users, PendingEventTypeEnum type, String message, Object object) {
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

}
