package it.uniroma2.dicii.ispw.gradely.enums;

public enum UserErrorMessagesEnum {

    /* === TITLES === */
    ROLE_ERROR_TITLE("Role error"),
    LOGIN_ERROR_TITLE("Login error"),
    DATA_RETRIEVAL_TITLE("Data retrieval error."),
    AUTHORIZATION_TITLE("Authorization error"),
    EXTERNAL_ERROR_TITLE("External error"),
    RESOURCE_LOADING_TITLE("Resource loading error"),
    PROPERTY_VALUE_TITLE("Property value error"),
    MISSING_VALUE("Missing value"),

    /* === MESSAGES === */
    ROLE_ERROR_MSG("The role associated with this user is not recognized."),
    WRONG_PASSWORD_MSG("Wrong password."),
    USER_NOT_FOUND_MSG("The user associated with this credentials does not exist."),
    MALFORMED_EMAIL_MSG("Please insert an email with the correct format."),
    MISSING_AUTHORIZATION_MSG("No role is associated with this user."),
    DATA_RETRIEVAL_MSG("An error occurred while retrieving user data."),
    MUR_TEST_RETRIEVAL_MSG("An error occurred while retrieving test info from MUR platform."),
    MOODLE_TEST_RETRIEVAL_MSG("An error occurred while retrieving test info from Moodle platform."),
    RESOURCE_LOADING_MSG("An error occurred while loading view resource."),
    PROPERTY_VALUE_MSG("Unexpected value of property name. Please check your configuration file."),
    SELECT_A_DEGREE_COURSE_MSG("Please select a degree course.");

    public final String message;

    UserErrorMessagesEnum(String message) {
        this.message = message;
    }
}
