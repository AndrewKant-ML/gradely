package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.beans_general.StudentBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.UserBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmAnagraphicalDataGraphicController implements Initializable {

    @FXML
    private TextField matricola, name, surname, email, codiceFiscale;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setAnagraphicalData(StudentBean studentBean, UserBean userBean) {
        this.matricola.setText(studentBean.getMatricola());
        this.name.setText(userBean.getName());
        this.surname.setText(userBean.getSurname());
        this.email.setText(userBean.getEmail());
        this.codiceFiscale.setText(userBean.getCodiceFiscale());
    }
}
