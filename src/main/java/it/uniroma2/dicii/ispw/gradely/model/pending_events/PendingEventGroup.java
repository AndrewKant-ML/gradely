package it.uniroma2.dicii.ispw.gradely.model.pending_events;

import it.uniroma2.dicii.ispw.gradely.enums.PendingEventTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class PendingEventGroup extends AbstractPendingEvent {
    private List<User> users; // TODO collapse User to codice fiscale

    public PendingEventGroup(List<User> users, PendingEventTypeEnum type, Object object) throws DAOException {
        super(type, object);
        this.users = users;
        super.notified = false;
    }

    @Override
    public Boolean isForUser(User user){
        return (this.users.contains(user));
    }

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }

}
