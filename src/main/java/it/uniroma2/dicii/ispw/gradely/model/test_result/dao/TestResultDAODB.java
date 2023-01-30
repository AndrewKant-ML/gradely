package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public class TestResultDAODB extends TestResultDAOAbstract {

    private TestResultDAODB(){
        super();
    }
    public static synchronized TestResultDAOAbstract getInstance(){
        if (instance == null) {
            instance = new TestResultDAODB();
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
