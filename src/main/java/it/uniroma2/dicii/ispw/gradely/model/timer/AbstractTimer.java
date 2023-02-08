package it.uniroma2.dicii.ispw.gradely.model.timer;

import it.uniroma2.dicii.ispw.gradely.exceptions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public abstract class AbstractTimer <T, O extends TimerObserver>{
    protected UUID id;
    protected LocalDate expiration;
    protected T object;
    protected List<O> observers; //can a timer have multiple observers? Snd multiple types?


    protected AbstractTimer(LocalDate expiration, T object){
        this.expiration = expiration;
        this.object = object;
    }

    public LocalDate getExpiration(){
        return expiration;
    }
    public void setExpiration(LocalDate expiration){
        this.expiration = expiration;
    }

    public T getObject(){
        return object;
    }

    public void setObject(T object){
        this.object = object;
    }

    public ExamConfirmationTimer castToExamConfirmationTimer() throws WrongTimerTypeException {
        return null;
    }
    public TestResultTimer castToTestResultTimer() throws WrongTimerTypeException{
        return null;
    }
    public void attach(O observer){
        this.observers.add(observer);
    }
    public void detach(O observer){
        this.observers.remove(observer);
    }
    public void notifyTimerExpiration() throws WrongTimerTypeException, DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException, MissingAuthorizationException, ObjectNotFoundException {
        for (O o : observers){
            o.timeIsUp(this);
        }
    }
}
