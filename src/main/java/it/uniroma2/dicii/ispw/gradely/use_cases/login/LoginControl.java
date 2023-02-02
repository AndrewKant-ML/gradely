package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.beans_general.LoginBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.UserBean;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.session_manager.SessionManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginControl {
    public LoginBean login(String email, String password) throws UserNotFoundException, WrongPasswordException, DAOException, MissingAuthorizationException {
        User user = UserLazyFactory.getInstance().getUserByEmail(email);
        user.checkPassword(password);
        String matricola;
        switch (user.getRole().getRoleEnumType()) {
            case STUDENT -> matricola = user.getRole().castToStudentRole().getMatricola();
            case PROFESSOR -> matricola = user.getRole().castToProfessorRole().getMatricola();
            default -> matricola = "";
        }
        return new LoginBean(
                SessionManager.getInstance().getSessionTokenKeyByUser(user),
                new UserBean(
                        user.getName(),
                        user.getSurname(),
                        user.getCodiceFiscale(),
                        user.getEmail(),
                        user.getRole().getRoleEnumType(),
                        matricola
                )
        );
    }

    public void emailMatches(String email) throws EmailFormatException {
        final Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        final Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches())
            throw new EmailFormatException(ExceptionMessagesEnum.EMAIL_FORMAT.message);
    }
}
