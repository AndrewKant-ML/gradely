package it.uniroma2.dicii.ispw.gradely.model.test.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public class TestDAODB extends TestDAOAbstract {

    private TestDAODB(){
        super();
    }
    public static synchronized TestDAOAbstract getInstance(){
        if (instance == null) {
            instance = new TestDAODB();
        }
        return instance;
    }


    @Override
    public void insert(AbstractTimer timer) {

    }

    @Override
    public void update(AbstractTimer timer) {

    }

    @Override
    public List<AbstractTimer> refresh(List<AbstractTimer> timers) {
        return null;
    }


}
