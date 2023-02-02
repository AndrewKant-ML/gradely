package it.uniroma2.dicii.ispw.gradely.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(String message){
        super(message);
    }

    public WrongPasswordException(String message, Throwable cause){
        super(message, cause);
    }
}
