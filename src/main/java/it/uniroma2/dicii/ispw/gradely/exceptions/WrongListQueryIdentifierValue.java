package it.uniroma2.dicii.ispw.gradely.exceptions;

public class WrongListQueryIdentifierValue extends Exception{
    public WrongListQueryIdentifierValue(String message) {
        super(message);
    }

    public WrongListQueryIdentifierValue(String message, Throwable cause) {
        super(message, cause);
    }
}
