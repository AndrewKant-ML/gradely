package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.model.PendingEvent;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.List;
import java.util.UUID;

public class Session {
    private User user;
    private Token token;


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

}
