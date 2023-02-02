package it.uniroma2.dicii.ispw.gradely.model.association_classes.test_reservation.dao;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.util.List;

public class TestDAODB extends TestDAOAbstract {

    private TestDAODB(){
        super();
    }
    public static synchronized TestDAOAbstract getInstance(){
        if (instance == null){
            instance = new TestDAODB();
        }
        return instance;
    }

    @Override
    public void insert(Test test){

    }

    @Override
    public void cancel(Test test){

    }

    @Override
    public void update(Test test){

    }

    @Override
    public List<Test> refresh(List<Test> tests){
        return null;
    }
}
