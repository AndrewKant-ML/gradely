package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.BaseGraphicControl;
import it.uniroma2.dicii.ispw.gradely.MainApplication;
import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.exceptions.EmailFormatException;
import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
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
    private Button registerButton, loginButton;

    private LoginControl loginController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginController = new LoginControl();
    }

    /**
     * Executes login operations
     * @param event the mouse click event
     */
    public void login(ActionEvent event) {
        final String email = this.emailField.getText();
        final String password = this.passwordField.getText();

        try {
            loginController.emailMatches(email);
            //Token loginToken = loginController.login(email, password);
            Token sessionToken = new Token(); // TODO remove this in production
            goToMainPage((Stage) ((Node) (event.getSource())).getScene().getWindow(), sessionToken);
        } catch (EmailFormatException efe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setContentText(efe.getMessage());
            alert.show();
        } catch (Exception e) { // TODO change exception type when changed on LoginControl
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens the user home page
     *
     * @param stage        the Stage object of the current application
     * @param sessionToken the session token generated after login
     */
    private void goToMainPage(Stage stage, Token sessionToken) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MainApplication.class.getResource("base_view.fxml")));
        try {
            Pane basePane = loader.load();
            BaseGraphicControl baseGraphicControl = loader.getController();
            baseGraphicControl.setSessionToken(sessionToken);
            PageNavigationController.getInstance().setBaseGraphicController(baseGraphicControl);
            PageNavigationController.getInstance().navigateTo("homepage");
            PageNavigationController.getInstance().setSessionToken(sessionToken);

            Scene scene = new Scene(basePane);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
