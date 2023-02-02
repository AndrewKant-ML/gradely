package it.uniroma2.dicii.ispw.gradely.exceptions;

public class EmailFormatException extends Exception {

    public EmailFormatException(String message) {
        super(message);
    }

    public EmailFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
