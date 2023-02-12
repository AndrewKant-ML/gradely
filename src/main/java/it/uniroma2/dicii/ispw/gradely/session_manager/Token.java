package it.uniroma2.dicii.ispw.gradely.session_manager;

import java.time.LocalDate;

public class Token {
    private String key; //TBI implementare chiave decente

    public Token(){
        this.key = LocalDate.now().toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
