package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.AbstractGraphicControl;
import it.uniroma2.dicii.ispw.gradely.beans.DegreeCourseBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class EnrollToDegreeCourseGraphicControl extends AbstractGraphicControl implements Initializable {

    @FXML
    private ListView<DegreeCourseBean> degreeCoursesList;

    private EnrollToDegreeCourseControl controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         controller = new EnrollToDegreeCourseControl();
    }
}
