package it.uniroma2.dicii.ispw.gradely.model.timer.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.timer.AbstractTimer;

import java.util.List;

public abstract class TimerDAOAbstract implements DAOInterface <AbstractTimer> {
    protected static TimerDAOAbstract instance;

    protected TimerDAOAbstract(){
    }
}
