package it.uniroma2.dicii.ispw.gradely.model.test.dao;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public class TestDAOFS extends TestDAOAbstract {

    private TestDAOFS(){
        super();
    }

    public static TestDAOAbstract getInstance(){
        if (instance == null) {
            instance = new TestDAOFS();
        }
        return instance;
    }

    @Override
    public void insert(Test test) {

    }

    @Override
    public void cancel(Test test) {

    }

    @Override
    public void update(Test test) {

    }

    @Override
    public List<Test> refresh(List<Test> tests) {
        return null;
    }
}
