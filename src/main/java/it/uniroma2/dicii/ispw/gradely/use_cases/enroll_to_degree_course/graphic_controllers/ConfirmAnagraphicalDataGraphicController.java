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
    private TextField name;
    private TextField surname;
    private TextField email;
    private TextField codiceFiscale;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setAnagraphicalData(UserData userData) {
        this.matricola.setText(userData.getUserMatricola());
        this.name.setText(userData.getUserName());
        this.surname.setText(userData.getUserSurname());
        this.email.setText(userData.getUserEmail());
        this.codiceFiscale.setText(userData.getUserCodiceFiscale());
    }
}
