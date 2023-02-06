package it.uniroma2.dicii.ispw.gradely.model.role.secretary;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class AbstractSecretaryDAO implements DAOInterface<Secretary> {
    protected static AbstractSecretaryDAO instance;

    protected AbstractSecretaryDAO(){
    }

    public abstract Secretary getSecretaryByUser(User user) throws DAOException, UserNotFoundException;
    public abstract List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento, List<Secretary> secretaryList) throws DAOException, UserNotFoundException, MissingAuthorizationException;

}
