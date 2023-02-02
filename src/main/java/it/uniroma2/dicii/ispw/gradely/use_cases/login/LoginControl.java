package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.beans_general.LoginBean;
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
        return new LoginBean(
                SessionManager.getInstance().getSessionTokenKeyByUser(user),
                user.getRole().getRoleEnumType().type
        );
    }

    public void emailMatches(String email) throws EmailFormatException {
        final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(\\?:.[a-zA-Z0-9-]+)$");
        final Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches())
            throw new EmailFormatException(ExceptionMessagesEnum.EMAIL_FORMAT.message);
    }
}
