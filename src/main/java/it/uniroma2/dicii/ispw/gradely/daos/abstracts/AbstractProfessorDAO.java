package it.uniroma2.dicii.ispw.gradely.daos.abstracts;

import it.uniroma2.dicii.ispw.gradely.model.Professor;
import it.uniroma2.dicii.ispw.gradely.model.User;

public abstract class AbstractProfessorDAO {
    protected static AbstractProfessorDAO instance;
    protected AbstractProfessorDAO(){ //TODO implementare costruttore vero
    }

    public abstract Professor getProfessorByUser(User user);
}
