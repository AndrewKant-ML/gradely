package it.uniroma2.dicii.ispw.gradely.exceptions;

public class BeanFormatException extends Exception{
    public BeanFormatException(String message) {
        super(message);
    }

    public BeanFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
