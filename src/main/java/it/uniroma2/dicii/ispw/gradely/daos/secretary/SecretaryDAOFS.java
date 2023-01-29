package it.uniroma2.dicii.ispw.gradely.daos.secretary;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.lazy_factories.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.User;

import java.util.ArrayList;
import java.util.List;

public class SecretaryDAOFS extends AbstractSecretaryDAO {

    private SecretaryDAOFS(){ //TODO implementare costruttore vero
        secretaries = new ArrayList<Secretary>();
        secretaries.add(new Secretary(UserLazyFactory.getInstance().getUserByEmail("m.rossi@uniroma2.it"), DipartimentoEnum.DICII));
    }

    public static AbstractSecretaryDAO getInstance(){
        if (instance == null) {
            instance = new SecretaryDAOFS();
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
