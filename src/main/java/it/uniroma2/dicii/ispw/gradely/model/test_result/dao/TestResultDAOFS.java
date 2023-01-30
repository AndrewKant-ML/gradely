package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public class TestResultDAOFS extends TestResultDAOAbstract {

    private TestResultDAOFS(){
        super();
    }

    public static TestResultDAOAbstract getInstance(){
        if (instance == null) {
            instance = new TestResultDAOFS();
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
