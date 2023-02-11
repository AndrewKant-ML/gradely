package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.PropertiesHandler;
import it.uniroma2.dicii.ispw.gradely.enums.FrontEndTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager instance;
    private final List<Session> activeSessions;

    private SessionManager() {
        activeSessions = new ArrayList<>();
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    private Session getSession(User user) {
        for (Session s : activeSessions) {
            if (s.getUser().equals(user)) {
                return s;
            }
        }
        return null;
    }
    private Session getSession(String tokenKey) {
        for (Session s : activeSessions) {
            if (s.getToken().getKey().equals(tokenKey)) {
                return s;
            }
        }
        return null;
    }

    public User getSessionUserByTokenKey(String tokenKey) {
        Session s = getSession(tokenKey);
        if (s == null) {
            return null;
        }
        return s.getUser();
    }

    public String getSessionTokenKeyByUser(User user) throws ResourceNotFoundException, PropertyException {
        Session s = getSession(user);
        if (s == null) {
            FrontEndTypeEnum frontEndType = FrontEndTypeEnum.getFrontEndTypeByValue(PropertiesHandler.getInstance().getProperty("front_end_type"));
            s = new Session(user, frontEndType);
            activeSessions.add(s);
        }
            return s.getToken().getKey();
        }
}
