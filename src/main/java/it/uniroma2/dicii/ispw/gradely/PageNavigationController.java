package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.beans_general.UserBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.loggers_general.GeneralLogger;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.UserData;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Objects;

public final class PageNavigationController {

    private static final String FILE_EXTENSION = ".fxml";
    private static PageNavigationController instance;
    private BaseGraphicControl baseGraphicController;
    private UserData userData;

    private PageNavigationController() {

    }

    public static synchronized PageNavigationController getInstance() {
        if (instance == null)
            instance = new PageNavigationController();
        return instance;
    }

    public void openMainPage(String sessionTokenKey, UserBean userBean) {
        setUserData(userBean, sessionTokenKey);
        String viewName = "";
        switch (userBean.getRole()) {
            case 1 -> viewName = "homepage_student";
            case 2 -> viewName = "homepage_professor";
            case 3 -> viewName = "homepage_secretary";
            default ->
                    showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.ROLE_ERROR_TITLE.message, UserErrorMessagesEnum.ROLE_ERROR_MSG.message);
        }
        viewName = viewName.concat(FILE_EXTENSION);
        try {
            baseGraphicController.openMainPage(
                    FXMLLoader.load(Objects.requireNonNull(PageNavigationController.class.getResource(viewName))),
                    String.format("%c%c", this.userData.getUserName().toUpperCase().charAt(0), this.userData.getUserSurname().toUpperCase().charAt(0)));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message, e);
        }
    }

    /**
     * Loads a specific .fxml file into a view and navigates to it
     *
     * @param pageName the name of the view (without the '.fxml' suffix) to be displayed
     */
    public void navigateTo(String pageName) {
        if (!pageName.endsWith(FILE_EXTENSION))
            pageName = pageName.concat(FILE_EXTENSION);
        try {
            baseGraphicController.switchTo(
                    FXMLLoader.load(Objects.requireNonNull(PageNavigationController.class.getResource(pageName))));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message, e);
        }
    }

    /**
     * Navigates to under construction page
     */
    public void navigateToUnderConstructionPage() {
        navigateTo("under_construction");
    }

    public void setBaseGraphicController(BaseGraphicControl baseGraphicController) {
        this.baseGraphicController = baseGraphicController;
    }

    public void returnToMainPage() {
        baseGraphicController.returnToMainPage();
    }

    public String getSessionTokenKey() {
        return this.userData.getSessionTokenKey();
    }

    public void setSessionTokenKey(String sessionTokenKey) {
        this.userData.setSessionTokenKey(sessionTokenKey);
    }

    public UserData getUserData() {
        return this.userData;
    }

    /**
     * Saves User data
     *
     * @param userBean a bean containing the User data
     */
    private void setUserData(UserBean userBean, String tokenKey) {
        this.userData = new UserData(
                userBean.getName(),
                userBean.getSurname(),
                userBean.getEmail(),
                userBean.getCodiceFiscale(),
                userBean.getMatricola(),
                userBean.getRole(),
                tokenKey
        );
    }

    // TBI implement user switch

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
        GeneralLogger.logSevere(e.getMessage());
        showAlert(alertType, title, message);
    }
}
