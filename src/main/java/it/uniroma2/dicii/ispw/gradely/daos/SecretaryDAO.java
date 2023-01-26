package it.uniroma2.dicii.ispw.gradely.daos;

import it.uniroma2.dicii.ispw.gradely.enums.TitleEnum;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SecretaryDAO {
    private static SecretaryDAO instance;
    private List<Secretary> secretaries;

    private SecretaryDAO(){ //TODO implementare costruttore vero
        secretaries = new ArrayList<Secretary>();
        secretaries.add(new Secretary(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it")));
    }

    public static SecretaryDAO getInstance(){
        if (instance == null) {
            instance = new SecretaryDAO();
        }
        return instance;
    }

    public Secretary getSecretaryByUser(User user) {
        for(Secretary s : secretaries){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }
}
