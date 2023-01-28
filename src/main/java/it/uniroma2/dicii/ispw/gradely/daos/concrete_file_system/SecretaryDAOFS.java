package it.uniroma2.dicii.ispw.gradely.daos.concrete_file_system;

import it.uniroma2.dicii.ispw.gradely.daos.abstracts.AbstractSecretaryDAO;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class SecretaryDAOFS extends AbstractSecretaryDAO {
    private static SecretaryDAOFS instance;
    private List<Secretary> secretaries;

    private SecretaryDAOFS(){ //TODO implementare costruttore vero
        secretaries = new ArrayList<Secretary>();
        secretaries.add(new Secretary(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it")));
    }

    public static SecretaryDAOFS getInstance(){
        if (instance == null) {
            instance = new SecretaryDAOFS();
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
