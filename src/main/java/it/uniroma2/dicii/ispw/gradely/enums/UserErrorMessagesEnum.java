package it.uniroma2.dicii.ispw.gradely.enums;

public enum UserErrorMessagesEnum {

    ROLE_ERROR_TITLE("Role error"),
    ROLE_ERROR_MSG("The role associated with this user is not recognized."),
    LOGIN_ERROR_TITLE("Login error"),
    WRONG_PASSWORD_MSG("Wrong password."),
    USER_NOT_FOUND_MSG("The user associated with this credentials does not exist."),
    MALFORMED_EMAIL_MSG("Please insert an email with the correct format."),
    MISSING_AUTHORIZATION_MSG("No role is associated with this user."),
    DATA_RETRIEVAL_TITLE("Data retrieval error."),
    DATA_RETRIEVAL_ERROR("An error occurred while retrieving user data."),
    ;

    public final String message;

    private UserErrorMessagesEnum(String message) {
        this.message = message;
    }
}
