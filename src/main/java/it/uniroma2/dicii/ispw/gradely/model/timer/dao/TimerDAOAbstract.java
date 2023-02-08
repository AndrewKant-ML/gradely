package it.uniroma2.dicii.ispw.gradely.model.timer.dao;

import it.uniroma2.dicii.ispw.gradely.dao_abstract.DAODBAbstract;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

public abstract class TimerDAOAbstract implements DAODBAbstract<AbstractTimer> {
    protected static TimerDAOAbstract instance;

    protected TimerDAOAbstract(){
    }
}
