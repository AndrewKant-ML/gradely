package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class PageNavigationController {
    private static PageNavigationController instance;
    private BaseGraphicControl baseGraphicController;
    private String sessionTokenKey;

    private PageNavigationController() {

    }

    public static synchronized PageNavigationController getInstance() {
        if (instance == null)
            instance = new PageNavigationController();
        return instance;
    }

    public void openMainPage(Stage stage, String sessionTokenKey, Integer userRole) {
        setSessionTokenKey(sessionTokenKey);
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MainApplication.class.getResource("base_view.fxml")));
        try {
            Pane basePane = loader.load();
            BaseGraphicControl baseGraphicControl = loader.getController();
            setBaseGraphicController(baseGraphicControl);
            String view_name = "";
            switch (userRole) {
                case 0 -> view_name = "homepage_student";
                case 1 -> view_name = "homepage_professor";
                case 2 -> view_name = "homepage_secretary";
                default -> {
                    showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.ROLE_ERROR_TITLE.message, UserErrorMessagesEnum.ROLE_ERROR_MSG.message);
                }
            }
            navigateTo(view_name);

            Scene scene = new Scene(basePane);
            stage.setScene(scene);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Loading error", ExceptionMessagesEnum.HOMEPAGE_LOAD_ERROR.message);
        }
    }

    public void setBaseGraphicController(BaseGraphicControl baseGraphicController) {
        this.baseGraphicController = baseGraphicController;
    }

    /**
     * Loads a specific .fxml file into a view and navigates to it
     *
     * @param pageName the name of the view (without the '.fxml' suffix) to be displayed
     */
    public void navigateTo(String pageName) {
        pageName = pageName.concat(".fxml");
        try {
            baseGraphicController.switchTo(
                    FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(pageName))));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Loading error", "");
        }
    }

    public void returnToMainPage() {
        baseGraphicController.returnToMainPage();
    }

    public String getSessionTokenKey() {
        return sessionTokenKey;
    }

    public void setSessionTokenKey(String sessionTokenKey) {
        this.sessionTokenKey = sessionTokenKey;
    }

    // TODO implement user switch

    /**
     * Display an alert with custom type, title and message.
     * This method manages the alerts globally on the current
     * instance of the application when GUI user interface is applied
     *
     * @param alertType the type of Alert to be displayed
     * @param title     the title of the Alert pane
     * @param message   the message of the Alert pane
     */
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Creates an alert message relative to a specific thrown exception,
     * with custom type, title and message. This method manages the alerts globally on the current
     * * instance of the application when GUI user interface is applied
     *
     * @param alertType the type of Alert to be displayed
     * @param title     the title of the Alert pane
     * @param message   the message of the Alert pane
     * @param e         the Exception which caused the Alert to be shown
     */
    public void showAlert(Alert.AlertType alertType, String title, String message, Exception e) {
        Logger.printErr(String.format(message + ". Error message: %s", e.getMessage()));
        showAlert(alertType, title, String.format(message + ". Error message: %s", e.getMessage()));
    }
}
