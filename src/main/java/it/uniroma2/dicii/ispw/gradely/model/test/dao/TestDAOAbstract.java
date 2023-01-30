package it.uniroma2.dicii.ispw.gradely.model.test.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public abstract class TestDAOAbstract {
    protected static TestDAOAbstract instance;

    protected TestDAOAbstract(){
    }

    public abstract void insert(AbstractTimer timer);
    public abstract void update(AbstractTimer timer);
    public abstract List<AbstractTimer> refresh(List<AbstractTimer> timers);
}
