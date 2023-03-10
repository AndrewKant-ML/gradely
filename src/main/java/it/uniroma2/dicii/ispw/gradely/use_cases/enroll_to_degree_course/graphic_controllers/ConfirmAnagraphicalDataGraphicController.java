package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans.UserData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmAnagraphicalDataGraphicController implements Initializable {

    @FXML
    private TextField matricola;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField codiceFiscale;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Automatically called after FXMLLoader::load() method for initialization
    }

    public void setAnagraphicalData(UserData userData) {
        this.matricola.setText(userData.getUserMatricola());
        this.name.setText(userData.getUserName());
        this.surname.setText(userData.getUserSurname());
        this.email.setText(userData.getUserEmail());
        this.codiceFiscale.setText(userData.getUserCodiceFiscale());
    }
}
