package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.model.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.User;

public abstract class AbstractSecretaryDAO {
    protected static AbstractSecretaryDAO instance;

    protected AbstractSecretaryDAO(){ //TODO implementare costruttore vero
    }

    public abstract Secretary getSecretaryByUser(User user);
}
