package it.uniroma2.dicii.ispw.gradely.exceptions;

public class WrongDegreeCourseCodeException extends Exception{
    public WrongDegreeCourseCodeException(String message) {
        super(message);
    }

    public WrongDegreeCourseCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
