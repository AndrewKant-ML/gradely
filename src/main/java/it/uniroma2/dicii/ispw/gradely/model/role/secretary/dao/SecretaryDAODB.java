package it.uniroma2.dicii.ispw.gradely.model.role.secretary.dao;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class SecretaryDAODB extends AbstractSecretaryDAO {

    private SecretaryDAODB(){ //TODO implementare costruttore vero
        secretaries = new ArrayList<Secretary>();
        secretaries.add(new Secretary(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), DipartimentoEnum.DICII));
    }

    public static AbstractSecretaryDAO getInstance(){
        if (instance == null) {
            instance = new SecretaryDAODB();
        }
        return instance;
    }

    @Override
    public Secretary getSecretaryByUser(User user) {
        for(Secretary s : secretaries){
            if(s.getUser().equals(user)) {
                return s; //TODO implementare exception
            }
        }
        return null; //TODO implementare exceptions
    }

    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento) {
        return null;
    }

    @Override
    public void update(Secretary secretary) {

    }

    @Override
    public List<Secretary> refresh(List<Secretary> secretaries) {
        return null;
    }
}
