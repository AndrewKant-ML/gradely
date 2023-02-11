package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CareerGraphicController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void enrollToDegreeCourse() {
        PageNavigationController.getInstance().navigateTo("enroll_to_degree_course");
    }

    @FXML
    private void underConstruction() {
        PageNavigationController.getInstance().navigateToUnderConstructionPage();
    }
}
