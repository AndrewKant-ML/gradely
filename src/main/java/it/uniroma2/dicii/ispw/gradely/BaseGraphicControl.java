package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.beans_general.PendingEventBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.facades.UserFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BaseGraphicControl implements Initializable {

    @FXML
    private StackPane content;
    @FXML
    private Button backButton;
    private final UserFacade facade;
    @FXML
    private Button accountButton;
    @FXML
    private Button notificationButton;
    @FXML
    private VBox pendingEventList;
    private List<PendingEventBean> pendingEvents;

    public BaseGraphicControl() {
        this.facade = new UserFacade();
        pendingEvents = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setVisible(false);
    }

    /**
     * Removes last stacked element from StackPane content
     */
    @FXML
    private void goBack() {
        content.getChildren().remove(content.getChildren().size() - 1);
        if (content.getChildren().size() == 1)
            backButton.setVisible(false);
    }

    /**
     * Add a node to the StackPane content, i.e. switch
     * to the node-relative application page
     *
     * @param node the node to be added to the content
     */
    public void switchTo(Node node){
        content.getChildren().add(node);
        if (content.getChildren().size() >= 2)
            backButton.setVisible(true);
    }

    /**
     * Returns to the main page by removing all the
     * levels stacked while navigating
     */
    void returnToMainPage() {
        while (content.getChildren().size() > 1)
            goBack();
    }

    /**
     * Opens the application main page
     *
     * @param node the node containing the main page
     * @param name the name to be shown in the account button
     */
    void openMainPage(Node node, String name) {
        content.getChildren().clear();
        content.getChildren().add(node);
        accountButton.setText(name);
        accountButton.setVisible(true);
        retrievePendingEvents();
        notificationButton.setVisible(true);
    }

    /**
     * Retrieves pending events not yet notified
     */
    private void retrievePendingEvents() {
        try {
            List<PendingEventBean> newPendingEvents = facade.retrievePendingEvents(PageNavigationController.getInstance().getSessionTokenKey());
            this.pendingEvents.addAll(newPendingEvents);
            for (PendingEventBean newPendingEvent : newPendingEvents) {
                String pendingEventViewName;
                switch (newPendingEvent.getType()) {

                }
            }
        } catch (DAOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message);
        }
    }

    /**
     * Displays pending events to the user
     */
    @FXML
    private void showPendingEvents() {
        retrievePendingEvents();
        pendingEventList.setVisible(!pendingEventList.isVisible());
    }
}