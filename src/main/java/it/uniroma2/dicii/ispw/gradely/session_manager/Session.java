package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.enums.FrontEndTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class Session {
    private User user;
    private Token token;

    private FrontEndTypeEnum frontEndType;


    public Session(User user, FrontEndTypeEnum frontEndType){
        this.user = user;
        this.token = new Token();
        this.frontEndType = frontEndType;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Token getToken(){
        return token;
    }

    public void setToken(Token token){
        this.token = token;
    }

    public FrontEndTypeEnum getFrontEndType(){
        return frontEndType;
    }

    public void setFrontEndType(FrontEndTypeEnum frontEndType){
        this.frontEndType = frontEndType;
    }

}
