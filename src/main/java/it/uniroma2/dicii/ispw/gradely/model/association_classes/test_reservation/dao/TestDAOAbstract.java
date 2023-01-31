package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public abstract class TestDAOAbstract implements DAOInterface<Test> {
    protected static TestDAOAbstract instance;

    protected TestDAOAbstract(){
    }

}
