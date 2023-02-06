package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public class SecretaryDAOFS extends AbstractSecretaryDAO {

    private SecretaryDAOFS(){ 

    }

    public static synchronized AbstractSecretaryDAO getInstance(){
        if (instance == null){
            instance = new SecretaryDAOFS();
        }
        return instance;
    }

    @Override
    public Secretary getSecretaryByUser(User user) throws DAOException {
        return null; 
    }

    @Override
    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> secretaryList) throws DAOException, UserNotFoundException {
        return null;
    }

    @Override
    public void insert(Secretary secretary){

    }

    @Override
    public void cancel(Secretary secretary){

    }

    @Override
    public void update(Secretary secretary){

    }

    @Override
    public List<Secretary> refresh(List<Secretary> secretaries){
        return null;
    }
}
