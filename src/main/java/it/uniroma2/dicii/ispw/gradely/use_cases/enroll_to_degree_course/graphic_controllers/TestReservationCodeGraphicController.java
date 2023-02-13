package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TestReservationCodeGraphicController implements Initializable {

    @FXML
    private TextField testReservationCodeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        // Automatically called after FXMLLoader::load() method for initialization
    }

    public void setTestReservationCode(String code){
        testReservationCodeLabel.setText(code);
    }
}
