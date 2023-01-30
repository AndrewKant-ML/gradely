package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_factories.DAOFactory;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

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
        return DAOFactory.getDAOFactory().getSecretaryDAO().getSecretaryByUser(user); //TODO implementare exception
    }



    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento){
        List<Secretary> list = new ArrayList<>();
        for(Secretary s : secretaries){
            if(s.getDipartimento().equals(dipartimento)) {
                list.add(s); //TODO implementare exception
            }
        }
        List<Secretary> daoList = DAOFactory.getDAOFactory().getSecretaryDAO().getSecretariesByDipartimento(dipartimento); //TODO implementare exception
        for(Secretary s : daoList){
            if(s.getDipartimento().equals(dipartimento)) {
                list.add(s); //TODO implementare exception
            }
        }
        return list;
    }
}
