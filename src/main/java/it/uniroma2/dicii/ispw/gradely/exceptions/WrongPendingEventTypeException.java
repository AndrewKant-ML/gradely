package it.uniroma2.dicii.ispw.gradely.exceptions;

public class WrongPendingEventTypeException extends Exception{

    public WrongPendingEventTypeException(String message) {
        super(message);
    }

    public WrongPendingEventTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
