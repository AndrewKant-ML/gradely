package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.model.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session {
    private User user;
    private Token token;
    private List<PendingEvent> pendingEvents;

    public Session(User user) {
        this.user = user;
        this.token = new Token();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public List<PendingEvent> getPendingEvents() {
        return pendingEvents;
    }

    public void setPendingEvents(List<PendingEvent> pendingEvents) {
        this.pendingEvents = pendingEvents;
    }

    public Boolean checkUUID(UUID id){
        for(PendingEvent event : pendingEvents){
            if(id.equals(event.getID())){
                return true;
            }
        }
        return false;
    }
}
