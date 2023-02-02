package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.WrongTimerTypeException;

public interface TimerObserver {
    void timeIsUp(AbstractTimer timer) throws WrongTimerTypeException, DAOException;
}
