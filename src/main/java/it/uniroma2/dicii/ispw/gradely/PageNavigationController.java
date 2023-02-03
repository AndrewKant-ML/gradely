package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.beans_general.UserBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Objects;

public final class PageNavigationController {
    private static PageNavigationController instance;
    private BaseGraphicControl baseGraphicController;
    private String sessionTokenKey;
    private UserBean userBean;

    private PageNavigationController() {

    }

    public static synchronized PageNavigationController getInstance() {
        if (instance == null)
            instance = new PageNavigationController();
        return instance;
    }

    public void openMainPage(String sessionTokenKey, UserBean userBean) {
        setSessionTokenKey(sessionTokenKey);
        setUserBean(userBean);
        String viewName = "";
        switch (userBean.getRole().type) {
            case 0 -> viewName = "homepage_student";
            case 1 -> viewName = "homepage_professor";
            case 2 -> viewName = "homepage_secretary";
            default ->
                    showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.ROLE_ERROR_TITLE.message, UserErrorMessagesEnum.ROLE_ERROR_MSG.message);
        }
        viewName = viewName.concat(".fxml");
        try {
            baseGraphicController.openMainPage(
                    FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(viewName))));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message, e);
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
            showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message, e);
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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
        e.printStackTrace();
        //Logger.printErr(String.format("Error message: %s", e.getMessage()));
        showAlert(alertType, title, message);
    }
}
