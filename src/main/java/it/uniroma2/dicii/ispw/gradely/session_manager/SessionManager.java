package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager instance;
    private List<Session> activeSessions;
    private SessionManager(){
        activeSessions = new ArrayList<Session>();
    }
    public static SessionManager getInstance(){
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    public Session getSession(User user){
        List<Session> list = new ArrayList<Session>();
        for(Session s : activeSessions){
            if(s.getUser().equals(user)) {
                return s;
            }
        }
        return null;
    }

    public Session getSession(Token token){
        List<Session> list = new ArrayList<Session>();
        for(Session s : activeSessions){
            if(s.getToken().equals(token)) {
                return s;
            }
        }
        return null;
    }

    public Token getLazySessionToken(User user){
        Session s = getSession(user);
        if (s == null) {
            s = new Session(user);
            activeSessions.add(s);
        }
        return s.getToken();
    }


}
