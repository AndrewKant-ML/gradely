package it.uniroma2.dicii.ispw.gradely.model.timer.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOAbstract;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

public abstract class TimerDAOAbstract implements DAOAbstract<AbstractTimer> {
    protected static TimerDAOAbstract instance;

    protected TimerDAOAbstract(){
    }
}
