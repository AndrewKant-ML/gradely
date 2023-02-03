package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.LoginBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginGraphicControl implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private LoginControl loginController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loginController = new LoginControl();
        loginButton.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                login();
        });
    }

    /**
     * Executes login graphical operation
     */
    public void login() {
        final String email = this.emailField.getText();
        final String password = this.passwordField.getText();
        try {
            loginController.emailMatches(email);
            LoginBean loginBean = loginController.login(email, password);
            PageNavigationController.getInstance().openMainPage(loginBean.getTokenKey(), loginBean.getUserBean());
        } catch (EmailFormatException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.LOGIN_ERROR_TITLE.message, UserErrorMessagesEnum.MALFORMED_EMAIL_MSG.message, e);
        } catch (UserNotFoundException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.LOGIN_ERROR_TITLE.message, UserErrorMessagesEnum.USER_NOT_FOUND_MSG.message, e);
        } catch (DAOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message, e);
        } catch (WrongPasswordException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.LOGIN_ERROR_TITLE.message, UserErrorMessagesEnum.WRONG_PASSWORD_MSG.message, e);
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.LOGIN_ERROR_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message, e);
        }
    }
}
