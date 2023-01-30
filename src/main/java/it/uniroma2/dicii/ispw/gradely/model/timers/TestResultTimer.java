package it.uniroma2.dicii.ispw.gradely.model.timers;

import it.uniroma2.dicii.ispw.gradely.model.test.Test;

import java.time.LocalDate;

public class TestResultTimer extends AbstractTimer{
    private Test test;

    public TestResultTimer(Test test, LocalDate expiration) {
        super(expiration);
        this.test = test;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
