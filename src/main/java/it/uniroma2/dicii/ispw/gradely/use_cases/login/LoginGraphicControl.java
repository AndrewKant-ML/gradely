package it.uniroma2.dicii.ispw.gradely.use_cases.login;

import it.uniroma2.dicii.ispw.gradely.BaseGraphicControl;
import it.uniroma2.dicii.ispw.gradely.MainApplication;
import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            //loginController.emailMatches(email);
            Token sessionToken = loginController.login(email, password);
            goToMainPage((Stage) node.getScene().getWindow(), sessionToken);
        } /*catch (EmailFormatException efe) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Login error", "Email format error", e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setContentText(efe.getMessage());
            alert.show();
        } */ catch (Exception e) { // TODO change exception type when changed on LoginControl
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Login error", "Wrong credentials", e);
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
            PageNavigationController.getInstance().setBaseGraphicController(baseGraphicControl);
            PageNavigationController.getInstance().navigateTo("homepage");
            PageNavigationController.getInstance().setSessionToken(sessionToken);

            Scene scene = new Scene(basePane);
            stage.setScene(scene);
        } catch (IOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, "Loading error", "Error while loading Homepage view");
        }
    }
}
