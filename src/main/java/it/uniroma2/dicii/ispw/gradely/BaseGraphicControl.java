package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseGraphicControl implements Initializable {

    private Token sessionToken;

    @FXML
    private StackPane content;

    @FXML
    private Button backButton;

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
    public void switchTo(Node node) {
        content.getChildren().add(node);
        if (content.getChildren().size() == 2)
            backButton.setVisible(true);

        System.out.println(sessionToken.getKey());
    }

    public void setSessionToken(Token sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Token getSessionToken() {
        return sessionToken;
    }
}