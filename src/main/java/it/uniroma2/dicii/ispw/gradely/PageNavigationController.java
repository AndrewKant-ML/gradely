package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Objects;

public final class PageNavigationController {
    private static PageNavigationController INSTANCE;
    private BaseGraphicControl baseGraphicController;
    private Token sessionToken;

    private PageNavigationController() {

    }

    public static PageNavigationController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PageNavigationController();
        return INSTANCE;
    }

    public void setBaseGraphicController(BaseGraphicControl baseGraphicController) {
        this.baseGraphicController = baseGraphicController;
    }

    public void navigateTo(String pageName) {
        pageName = pageName.concat(".fxml");
        try {
            baseGraphicController.switchTo(
                    FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(pageName))));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Loading error", "");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading error");
            alert.setContentText(String.format("Error while loading view. Error message: %s", e.getMessage()));
            alert.show();
        }
    }

    public void returnToMainPage() {
        baseGraphicController.returnToMainPage();
    }

    public Token getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(Token sessionToken) {
        this.sessionToken = sessionToken;
    }

    // TODO implement user switch

    public void showAlert(Alert.AlertType alertType, String title, String message) {

    }

    /**
     * Creates an alert message relative to a specific thrown exception
     *
     * @param alertType the type of Alert to be displayed
     * @param title
     * @param message
     * @param e
     */
    public void showAlert(Alert.AlertType alertType, String title, String message, Exception e) {
        showAlert(alertType, title, String.format(message + ". Error message: %s", e.getMessage()));
    }
}
