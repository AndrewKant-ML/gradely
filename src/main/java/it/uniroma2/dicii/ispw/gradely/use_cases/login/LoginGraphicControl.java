package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.BaseGraphicControl;
import it.uniroma2.dicii.ispw.gradely.MainApplication;
import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.LoginBean;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
                login((Node) keyEvent.getSource());
        });
    }

    /**
     * Executes login operations
     *
     * @param event the mouse click event
     */
    public void login(ActionEvent event) {
        login((Node) event.getSource());
    }

    /**
     * Executes login graphical operation
     *
     * @param node the JavaFX node where the event has been triggered
     */
    private void login(Node node) {
        final String email = this.emailField.getText();
        final String password = this.passwordField.getText();

        try {
            loginController.emailMatches(email);
            LoginBean loginBean = loginController.login(email, password);
            goToMainPage((Stage) node.getScene().getWindow(), loginBean.getTokenKey());
        } catch (EmailFormatException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Login error", ExceptionMessagesEnum.EMAIL_FORMAT.message, e);
        } catch (UserNotFoundException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Login error", ExceptionMessagesEnum.USER_NOT_FOUND.message, e);
        } catch (DAOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Data retrieval error", ExceptionMessagesEnum.DAO.message, e);
        } catch (WrongPasswordException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Login error", ExceptionMessagesEnum.WRONG_PASSWORD.message, e);
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Login error", ExceptionMessagesEnum.MISSING_AUTH.message, e);
        }
    }

    /**
     * Opens the user home page
     *
     * @param stage        the Stage object of the current application
     * @param sessionToken the session token generated after login
     */
    private void goToMainPage(Stage stage, String tokenKey){
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MainApplication.class.getResource("base_view.fxml")));
        try {
            Pane basePane = loader.load();
            BaseGraphicControl baseGraphicControl = loader.getController();
            PageNavigationController.getInstance().setBaseGraphicController(baseGraphicControl);
            PageNavigationController.getInstance().navigateTo("homepage");
            PageNavigationController.getInstance().setSessionTokenKey(tokenKey);

            Scene scene = new Scene(basePane);
            stage.setScene(scene);
        } catch (IOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Loading error", "Error while loading Homepage view");
        }
    }
}
