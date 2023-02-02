package it.uniroma2.dicii.ispw.gradely.exceptions;

public class WrongTimerTypeException extends Exception{

    //MIGHT BE USELESS


    public WrongTimerTypeException(String message){
        super(message);
    }

    public WrongTimerTypeException(String message, Throwable cause){
        super(message, cause);
    }
}
