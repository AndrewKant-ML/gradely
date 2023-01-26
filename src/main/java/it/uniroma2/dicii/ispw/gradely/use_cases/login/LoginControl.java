package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.User;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;

public class LoginControl {
    public Token login(String email, String password) throws Exception{
        User user = UserLazyFactory.getInstance().getUser(email); //TODO implementare exception
        if(user.getPassword().equals(password)){// TODO implementare ricerca utente nel DAO
            return SessionManager.getInstance().getNewSession(user);
        }
        else throw new Exception(); //TODO implementare exception

    }
}
