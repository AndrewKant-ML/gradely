package it.uniroma2.dicii.ispw.gradely.model.timer.dao;

import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public class TimerDAODB extends TimerDAOAbstract {

    private TimerDAODB(){
        super();
    }
    public static synchronized TimerDAOAbstract getInstance(){
        if (instance == null) {
            instance = new TimerDAODB();
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
