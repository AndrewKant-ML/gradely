package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.beans_general.PendingEventBean;
import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.facades.UserFacade;
import it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.graphic.PendingEventExamVerbalizationGraphicController;
import it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.graphic.PendingEventGradeAcceptanceGraphicController;
import it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.graphic.PendingEventSimpleGraphicController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final List<PendingEventBean> pendingEvents;

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
                switch (newPendingEvent.getType()) {
                    case EXAM_VERBALIZATION_PENDING ->
                            pendingEventList.getChildren().add(loadPendingExamVerbalizationView(newPendingEvent));
                    case GRADE_CONFIRMATION_PENDING ->
                            pendingEventList.getChildren().add(loadPendingEventGradeAcceptanceView(newPendingEvent));
                    case EXAM_VERBALIZED, TEST_RESULT_READY, GRADE_AUTO_ACCEPTED ->
                            pendingEventList.getChildren().add(loadPendingEventSimpleView(newPendingEvent));
                    default ->
                            throw new WrongPendingEventTypeException(ExceptionMessagesEnum.UNEXPECTED_PROPERTY_NAME.message);
                }
            }
        } catch (DAOException | UserNotFoundException | WrongListQueryIdentifierValue | ObjectNotFoundException |
                 UnrecognizedRoleException | MissingAuthorizationException | WrongDegreeCourseCodeException |
                 WrongPendingEventTypeException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message);
        } catch (IOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message);
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

    private Node loadPendingEventSimpleView(PendingEventBean bean) throws IOException {
        String pendingEventViewName = "pending_event_simple.fxml";
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(BaseGraphicControl.class.getResource(pendingEventViewName)));
        Node node = loader.load();
        PendingEventSimpleGraphicController controller = loader.getController();
        controller.setMessage(bean.getType().message);
        return node;
    }

    private Node loadPendingEventGradeAcceptanceView(PendingEventBean bean) throws IOException {
        String pendingEventViewName = "pending_event_grade_acceptance.fxml";
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(BaseGraphicControl.class.getResource(pendingEventViewName)));
        Node node = loader.load();
        PendingEventGradeAcceptanceGraphicController controller = loader.getController();
        controller.setPendingEvent(bean);
        return node;
    }

    private Node loadPendingExamVerbalizationView(PendingEventBean bean) throws IOException {
        String pendingEventViewName = "pending_event_xam_verbalization.fxml";
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(BaseGraphicControl.class.getResource(pendingEventViewName)));
        Node node = loader.load();
        PendingEventExamVerbalizationGraphicController controller = loader.getController();
        controller.setPendingEvent(bean);
        return node;
    }
}