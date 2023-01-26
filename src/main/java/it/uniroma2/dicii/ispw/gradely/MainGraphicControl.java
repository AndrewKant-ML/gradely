package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainGraphicControl {
    //{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}