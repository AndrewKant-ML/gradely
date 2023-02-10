package it.uniroma2.dicii.ispw.gradely.enums;

public enum ExceptionMessagesEnum {
    HOMEPAGE_LOAD_ERROR("Error while loading Homepage view"),
    BEAN_FORMAT("Incorrect bean argument syntax"),
    DAO("An error occurred during data retrieval from the persistence layer"),
    EMAIL_FORMAT("Email address provided doesn't match with correct email format"),
    MISSING_AUTH("User doesn't have the authorization to execute the requested action"),
    OBJ_NOT_FOUND("Object requested is not present in the persistence strate"),
    TEST_RETRIEVAL_MOODLE("Error occurred while retrieving test info on Moodle platform"),
    TEST_RETRIEVAL_MUR("Error occurred while retrieving test info on MUR platform"),
    USER_NOT_FOUND("No user has been found with this credentials"),
    STUDENT_NOT_FOUND("No student has been found with this credentials"),
    PROFESSOR_NOT_FOUND("No professor has been found with this credentials"),
    SECRETARY_NOT_FOUND("No secretary has been found with this credentials"),
    WRONG_PASSWORD("Inserted email or password is incorrect"),
    WRONG_TIMER_TYPE(""),
    UNEXPECTED_PROPERTY_NAME("Unexpected property name"),
    RESOURCE_NOT_FOUND("The requested resource has not been found"),
    UNRECOGNIZED_ROLE("Unrecognized user role value"),
    WRONG_DEGREE_COURSE_CODE("Wrong degree course code"),
    WRONG_LIST_QUERY_IDENTIFIER_VALUE("Wrong list query id value")
    ;

    public final String message;

    private ExceptionMessagesEnum(String message) {
        this.message = message;
    }
}
