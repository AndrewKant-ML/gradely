package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.List;
import java.util.UUID;

public class PendingEventGroup extends PendingEvent{
    private List<User> users;

    public PendingEventGroup() {
    }
    public PendingEventGroup(List<User> users, PendingEventTypeEnum type, Object object) {
        UUID generatedId;
        do{
            generatedId=UUID.randomUUID();
        }while(SessionManager.getInstance().checkUUID(generatedId));

        super.id = generatedId;
        this.users = users;
        super.type = type;
        super.message = type.message;
        super.notified = false;
    }

    @Override
    public Boolean isForUser(User user){
        return (this.users.contains(user));
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
