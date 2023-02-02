package it.uniroma2.dicii.ispw.gradely.session_manager;

import java.time.LocalDate;

public class Token {
    private LocalDate key; //TODO implementare chiave decente

    public Token(){
        this.key=LocalDate.now();
    }

    public LocalDate getKey(){
        return key;
    }

    public void setKey(LocalDate key){
        this.key = key;
    }
}
