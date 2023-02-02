package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Objects;

public final class PageNavigationController {
    private static PageNavigationController instance;
    private BaseGraphicControl baseGraphicController;
    private String sessionTokenKey;

    private PageNavigationController(){

    }

    public static synchronized PageNavigationController getInstance(){
        if (instance == null)
            instance = new PageNavigationController();
        return instance;
    }

    public void setBaseGraphicController(BaseGraphicControl baseGraphicController){
        this.baseGraphicController = baseGraphicController;
    }

    public void navigateTo(String pageName){
        pageName = pageName.concat(".fxml");
        try {
            baseGraphicController.switchTo(
                    FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(pageName))));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Loading error", "");
        }
    }

    public void returnToMainPage(){
        baseGraphicController.returnToMainPage();
    }

    public String getSessionTokenKey(){
        return sessionTokenKey;
    }

    public void setSessionTokenKey(String sessionTokenKey){
        this.sessionTokenKey = sessionTokenKey;
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
