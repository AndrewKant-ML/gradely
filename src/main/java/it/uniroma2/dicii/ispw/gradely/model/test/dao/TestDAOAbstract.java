package it.uniroma2.dicii.ispw.gradely.model.test.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

public abstract class TestDAOAbstract implements DAOInterface<Test> {
    protected static TestDAOAbstract instance;

    protected TestDAOAbstract(){
    }

}
