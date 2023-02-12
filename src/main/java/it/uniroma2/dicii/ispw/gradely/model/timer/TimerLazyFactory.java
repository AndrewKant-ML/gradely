package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.dao_manager.DAOFactoryAbstract;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
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


    public static synchronized TimerLazyFactory getInstance() throws DAOException, UserNotFoundException, WrongTimerTypeException, PropertyException, WrongListQueryIdentifierValue, ObjectNotFoundException, ResourceNotFoundException, UnrecognizedRoleException, MissingAuthorizationException, WrongDegreeCourseCodeException {
        if (instance == null){
            instance = new TimerLazyFactory();
        }
        instance.checkTimers();
        return instance;
    }


    public AbstractTimer newExamConfirmationTimer(LocalDate expiration, Exam exam) throws DAOException, MissingAuthorizationException {
        AbstractTimer newTimer = new ExamConfirmationTimer(expiration, exam);
        try {
            DAOFactoryAbstract.getInstance().getTimerDAO().insert(newTimer);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        activeTimers.add(newTimer);
        return newTimer;
    }
    public AbstractTimer newTestResultTimer(LocalDate expiration, Test test) throws DAOException, MissingAuthorizationException {
        AbstractTimer newTimer = new TestResultTimer(expiration, test);
        try {
            DAOFactoryAbstract.getInstance().getTimerDAO().insert(newTimer);
        } catch (ResourceNotFoundException | PropertyException e) {
            throw new DAOException(ExceptionMessagesEnum.DAO.message, e);
        }
        activeTimers.add(newTimer);
        return newTimer;
    }
    private void checkTimers() throws WrongTimerTypeException, DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, ObjectNotFoundException, WrongListQueryIdentifierValue, UnrecognizedRoleException, WrongDegreeCourseCodeException { //TODO timered trigger
        refreshTimers();
        for (AbstractTimer t : activeTimers){
            if (t.getExpiration().isAfter(LocalDate.now())){
                t.notifyTimerExpiration();
                activeTimers.remove(t);
            }
        }
    }

    private void refreshTimers(){
        // To be implemented
    }
}