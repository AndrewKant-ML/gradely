package it.uniroma2.dicii.ispw.gradely.session_manager;

import it.uniroma2.dicii.ispw.gradely.model.User;

import java.time.LocalDate;

public class Token {
    private LocalDate key; //TODO chiave decente

    public Token() {
        this.key=LocalDate.now();
    }

    public LocalDate getKey() {
        return key;
    }

    public void setKey(LocalDate key) {
        this.key = key;
    }
}
