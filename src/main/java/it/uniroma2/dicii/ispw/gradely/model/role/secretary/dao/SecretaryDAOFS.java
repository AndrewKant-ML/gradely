package it.uniroma2.dicii.ispw.gradely.model.role.secretary.dao;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class SecretaryDAOFS extends AbstractSecretaryDAO {

    private SecretaryDAOFS(){ //TODO implementare costruttore vero

    }

    public static AbstractSecretaryDAO getInstance(){
        if (instance == null) {
            instance = new SecretaryDAOFS();
        }
        return instance;
    }

    @Override
    public Secretary getSecretaryByUser(User user) {
        return null; //TODO implementare exceptions
    }

    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento) {
        return null;
    }

    @Override
    public void insert(Secretary secretary) {

    }

    @Override
    public void update(Secretary secretary) {

    }

    @Override
    public List<Secretary> refresh(List<Secretary> secretaries) {
        return null;
    }
}
