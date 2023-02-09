package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
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

    public Secretary getSecretaryByUser(User user) throws DAOException, UserNotFoundException {
        for(Secretary s : secretaries){
            if(s.getUser().equals(user)){
                return s;
            }
        }
        try {
            return DAOFactoryAbstract.getInstance().getSecretaryDAO().getSecretaryByUser(user);
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
    }



    public List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento) throws DAOException, MissingAuthorizationException, ObjectNotFoundException, UnrecognizedRoleException {
        List<Secretary> list = new ArrayList<>();
        for(Secretary s : secretaries){
            if(s.getDipartimento().equals(dipartimento)){
                list.add(s); 
            }
        }
        try {
            List<Secretary> daoList = DAOFactoryAbstract.getInstance().getSecretaryDAO().getSecretariesByDipartimento(dipartimento, list);
            for (Secretary s : daoList) {
                if (s.getDipartimento().equals(dipartimento)) {
                    list.add(s);
                }
            }
        } catch (PropertyException | ResourceNotFoundException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
