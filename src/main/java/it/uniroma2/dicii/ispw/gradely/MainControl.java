package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import it.uniroma2.dicii.ispw.gradely.use_cases.login.LoginControl;

public class MainControl {
    //this is the main application controller
    public static void main(String [] args){
        LoginControl loginControl = new LoginControl();
        try{
            Token token = loginControl.login("m.rossi@uniroma2.it", "PWD.difficilissim4");
        }catch (Exception e) {
            throw new RuntimeException(e);
        } ;



    }
}

