package it.uniroma2.dicii.ispw.gradely.model.role.secretary.dao;

import it.uniroma2.dicii.ispw.gradely.enums.DipartimentoEnum;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class AbstractSecretaryDAO {
    protected static AbstractSecretaryDAO instance;
    protected List<Secretary> secretaries;

    protected AbstractSecretaryDAO(){
    }

    public abstract Secretary getSecretaryByUser(User user);
    public abstract List<Secretary> getSecretariesByDipartimento(DipartimentoEnum dipartimento);
    public abstract void update(Secretary secretary);

    public abstract List<Secretary> refresh(List<Secretary> secretaries);

}
