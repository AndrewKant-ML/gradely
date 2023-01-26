package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseGraphicControl implements Initializable {

    @FXML
    private AnchorPane content;

    private AbstractGraphicControl contentControl;
    private final Double topAnchor = 23.0;
    private final Double sideAnchor = 35.0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        content.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        try {
            // Load FXML view
            Parent view = loader.load();

            // Set this as view parent controller
            contentControl = loader.getController();
            contentControl.setParentControl(this);

            // Add child view to current view
            AnchorPane.setTopAnchor(view, topAnchor);
            AnchorPane.setRightAnchor(view, sideAnchor);
            AnchorPane.setLeftAnchor(view, sideAnchor);
            content.getChildren().add(view);
        } catch (IOException e) {
            System.err.println("Unable to load homepage.fxml");
            throw new RuntimeException(e);
        }
    }

    public void switchTo(String viewName) {
        viewName = viewName.concat(".fxml");
        System.out.println(viewName);
        // TODO implement page switching
    }
}