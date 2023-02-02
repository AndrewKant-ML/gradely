package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.enums.FrontEndTypeEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public class Session {
    private User user;
    private Token token;
    private FrontEndTypeEnum frontEndType;


    Session(User user, FrontEndTypeEnum frontEndType){
        this.user = user;
        this.token = new Token();
        this.frontEndType = frontEndType;
    }

    User getUser(){
        return user;
    }

    void setUser(User user){
        this.user = user;
    }

    Token getToken(){
        return token;
    }

    void setToken(Token token){
        this.token = token;
    }

    FrontEndTypeEnum getFrontEndType(){
        return frontEndType;
    }

    void setFrontEndType(FrontEndTypeEnum frontEndType){
        this.frontEndType = frontEndType;
    }

}
