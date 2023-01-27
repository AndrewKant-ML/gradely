package it.uniroma2.dicii.ispw.gradely.lazy_factories;

import it.uniroma2.dicii.ispw.gradely.daos.SecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.model.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class SecretaryLazyFactory {
    private static SecretaryLazyFactory instance;
    private List<Secretary> secretaries;

    private SecretaryLazyFactory(){
        secretaries = new ArrayList<Secretary>();
    }

    public static SecretaryLazyFactory getInstance(){
        if (instance == null) {
            instance = new SecretaryLazyFactory();
        }
        return instance;
    }

    public Secretary getSecretaryByUser(User user) {
        for(Secretary s : secretaries){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return SecretaryDAO.getInstance().getSecretaryByUser(user); //TODO implementare exception
    }
}
