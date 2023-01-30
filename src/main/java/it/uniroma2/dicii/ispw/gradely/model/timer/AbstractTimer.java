package it.uniroma2.dicii.ispw.gradely.model.timer;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractTimer <T, O extends TimerObserver>{
    protected LocalDate expiration;
    protected T t;
    protected List<O> observers;


    public AbstractTimer(LocalDate expiration, T t) {
        this.expiration = expiration;
        this.t = t;
    }

    public LocalDate getExpiration() {
        return expiration;
    }
    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
    public ExamConfirmationTimer examConfirmationTimer(){
        return null;
    }
    public TestResultTimer testResultTimer(){
        return null;
    }
    public void attach(O observer){
        this.observers.add(observer);
    }
    public void detach(O observer){
        this.observers.remove(observer);
    }
    public void notifyTimerExpiration(){
        for (O o : observers) {
            o.timeIsUp(this);
        }
    }
}
