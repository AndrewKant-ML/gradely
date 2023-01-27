package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.SelectDegreeCourseGraphicControl;
import it.uniroma2.dicii.ispw.gradely.general_beans.DegreeCourseBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class EnrollToDegreeCourseGraphicControl implements Initializable {

    @FXML
    private ListView<String> degreeCoursesList;

    @FXML
    private Button backButton, nextButton;

    @FXML
    private Parent firstStage, secondStage, thirdStage;
    private Integer currentStage;

    @FXML
    private SelectDegreeCourseGraphicControl firstStageController;

    @FXML
    private InsertAnagraphicalData secondStageController;

    private EnrollToDegreeCourseControl controller;

    private DegreeCourseBean selectedDegreeCourse;

    private Object anagraphicalData;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new EnrollToDegreeCourseControl();
        currentStage = 1;
        //firstStageController.setDegreeCoursesList(controller.getDegreeCourses());
    }

    public void nextStage() {
        switch (currentStage) {
            case 1 -> goToStageTwo();
            case 2 -> goToStageThree();
        }
        currentStage += currentStage >= 3 ? 0 : 1;
    }

    public void previousStage() {
        currentStage -= currentStage <= 1 ? 0 : 1;
    }

    private void goToStageTwo() {
        selectedDegreeCourse = firstStageController.getSelectedDegreeCourse();
        secondStage.setVisible(true);
    }

    private void goToStageThree() {
        anagraphicalData = secondStageController.getAnagraphicalData();
        thirdStage.setVisible(true);
    }
}
