package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseGraphicControl implements Initializable {

    @FXML
    private StackPane content;

    @FXML
    private Button backButton;

    @FXML
    private Button accountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setVisible(false);
    }

    /**
     * Removes last stacked element from StackPane content
     */
    public void goBack() {
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
        showPendingEvents();
    }


    void showPendingEvents() {

    }
}