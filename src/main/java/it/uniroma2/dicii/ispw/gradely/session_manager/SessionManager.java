package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.model.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static it.uniroma2.dicii.ispw.gradely.enums.FrontEndTypeEnum.*;

public class SessionManager {
    private static SessionManager instance;
    private List<Session> activeSessions;
    private List<PendingEvent> pendingEvents;
    private SessionManager(){
        activeSessions = new ArrayList<Session>();
    }
    public static SessionManager getInstance(){
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    private Session getSession(User user){
        for(Session s : activeSessions){
            if(s.getUser().equals(user)) {
                return s;
            }
        }
        return null;
    }

    private Session getSession(Token token){
        for(Session s : activeSessions){
            if(s.getToken().equals(token)) {
                return s;
            }
        }
        return null;
    }
    public User getLazySessionUser(Token token){
        Session s = getSession(token);
        if (s == null) {
            return null;
        }
        return s.getUser();
    }
    public Token getLazySessionToken(User user){
        Session s = getSession(user);
        if (s == null) {
            s = new Session(user,GUI,DB); //TODO implementare
            activeSessions.add(s);
        }
        return s.getToken();
    }
    public List<PendingEvent> getPendingEvents() {
        return pendingEvents;
    }

    public void setPendingEvents(List<PendingEvent> pendingEvents) {
        this.pendingEvents = pendingEvents;
    }

    public Boolean checkUUID(UUID id){
        for(PendingEvent event : pendingEvents){
            if(id.equals(event.getId())){
                return true;
            }
        }
        return false;
    }

}
