package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.WrongTimerTypeException;
import it.uniroma2.dicii.ispw.gradely.model.exam.Exam;
import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimerLazyFactory {
    private static TimerLazyFactory instance;
    private List<AbstractTimer> activeTimers;

    private TimerLazyFactory(){
        activeTimers = new ArrayList<>();
    }


    public static synchronized TimerLazyFactory getInstance(){
        if (instance == null){
            instance = new TimerLazyFactory();
        }
        return instance;
    }


    public AbstractTimer newExamConfirmationTimer(LocalDate expiration, Exam exam) throws DAOException {
        AbstractTimer newTimer = new ExamConfirmationTimer(expiration, exam);
        DAOFactoryAbstract.getInstance().getTimerDAO().insert(newTimer);
        activeTimers.add(newTimer);
        return newTimer;
    }
    public AbstractTimer newTestResultTimer(LocalDate expiration, Test test) throws DAOException {
        AbstractTimer newTimer = new TestResultTimer(expiration, test);
        DAOFactoryAbstract.getInstance().getTimerDAO().insert(newTimer);
        activeTimers.add(newTimer);
        return newTimer;
    }
    private void checkTimers() throws WrongTimerTypeException { //TODO timered trigger
        for (AbstractTimer t : activeTimers){
            if (t.getExpiration().isAfter(LocalDate.now())){
                t.notifyTimerExpiration();
                activeTimers.remove(t);
            }
        }
    }



}