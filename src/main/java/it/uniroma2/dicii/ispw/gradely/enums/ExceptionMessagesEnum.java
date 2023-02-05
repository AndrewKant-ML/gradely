package it.uniroma2.dicii.ispw.gradely.enums;

public enum ExceptionMessagesEnum {
    HOMEPAGE_LOAD_ERROR("Error while loading Homepage view"),
    DAO("An error occurred during data retrieval from the persistence layer"),
    EMAIL_FORMAT("Email address provided doesn't match with correct email format"),
    MISSING_AUTH("User doesn't have the authorization to execute the requested action"),
    OBJ_NOT_FOUND("Object requested is not present in the persistence strate"),
    TEST_RETRIVAL_MOODLE("Error occurred while retrieving test info on Moodle platform"),
    TEST_RETRIVAL_MUR("Error occurred while retrieving test info on MUR platform"),
    USER_NOT_FOUND("No user has benn found with this credentials"),
    WRONG_PASSWORD("Inserted email or password is incorrect"),
    WRONG_TIMER_TYPE(""),
    UNEXPECTED_PROPERTY_NAME("Unexpected property name"),
    RESOURCE_NOT_FOUND("The requested resource has not been found")
    ;

    public final String message;

    private ExceptionMessagesEnum(String message) {
        this.message = message;
    }
}
