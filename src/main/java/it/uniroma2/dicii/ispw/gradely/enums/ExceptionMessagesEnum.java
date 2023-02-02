package it.uniroma2.dicii.ispw.gradely.enums;

public enum ExceptionMessagesEnum {
    DAO(""),
    EMAIL_FORMAT("Email address provided doesn't match with the canonical email format"),
    MISSING_AUTH("User doesn't have the authorization to execute the requested action"),
    OBJ_NOT_FOUND("Object requested is not present in the persistence strate"),
    TEST_RETRIVAL(""),
    USER_NOT_FOUND(""),
    WRONG_PASSWORD("Inserted email or password is incorrect"),
    WRONG_TIMER_TYPE("");


    public final String message;

    private ExceptionMessagesEnum(String message) {
        this.message = message;
    }
}
