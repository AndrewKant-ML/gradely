package it.uniroma2.dicii.ispw.gradely.model.timers;

import java.time.LocalDate;

public abstract class AbstractTimer {
    protected LocalDate expiration;

    public AbstractTimer(LocalDate expiration) {
        this.expiration = expiration;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
