package it.uniroma2.dicii.ispw.gradely.model.test_result.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public abstract class TestResultDAOAbstract {
    protected static TestResultDAOAbstract instance;

    protected TestResultDAOAbstract(){
    }

    public abstract void insert(AbstractTimer timer);
    public abstract void update(AbstractTimer timer);
    public abstract List<AbstractTimer> refresh(List<AbstractTimer> timers);
}
