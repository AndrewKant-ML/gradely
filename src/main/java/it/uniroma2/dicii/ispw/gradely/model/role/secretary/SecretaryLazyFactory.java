package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class SecretaryLazyFactory {
    private static SecretaryLazyFactory instance;
    private List<Secretary> secretaries;

    private SecretaryLazyFactory(){
        secretaries = new ArrayList<Secretary>();
    }

    public static synchronized SecretaryLazyFactory getInstance(){
        if (instance == null){
            instance = new SecretaryLazyFactory();
        }
        return instance;
    }

    public Secretary getSecretaryByUser(User user) throws DAOException {
        for(Secretary s : secretaries){
            if(s.getUser().equals(user)){
                return s; 
            }
        }
        return DAOFactoryAbstract.getInstance().getSecretaryDAO().getSecretaryByUser(user);
    }



    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento) throws DAOException {
        List<Secretary> list = new ArrayList<>();
        for(Secretary s : secretaries){
            if(s.getDipartimento().equals(dipartimento)){
                list.add(s); 
            }
        }
        List<Secretary> daoList = DAOFactoryAbstract.getInstance().getSecretaryDAO().getSecretariesByDipartimento(dipartimento);
        for(Secretary s : daoList){
            if(s.getDipartimento().equals(dipartimento)){
                list.add(s); 
            }
        }
        return list;
    }
}
