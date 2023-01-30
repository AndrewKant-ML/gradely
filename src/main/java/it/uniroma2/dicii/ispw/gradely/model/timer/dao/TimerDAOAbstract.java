package it.uniroma2.dicii.ispw.gradely.model.timer.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public abstract class TimerDAOAbstract {
    protected static TimerDAOAbstract instance;

    protected TimerDAOAbstract(){
    }

    public abstract void insert(AbstractTimer timer);
    public abstract void update(AbstractTimer timer);
    public abstract List<AbstractTimer> refresh(List<AbstractTimer> timers);
}
