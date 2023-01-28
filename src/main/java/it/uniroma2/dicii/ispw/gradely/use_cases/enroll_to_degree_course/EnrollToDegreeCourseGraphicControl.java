package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

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

    @FXML
    private SelectDegreeCourseGraphicControl firstStageController;

    @FXML
    private ConfirmAnagraphicalData secondStageController;

    private EnrollToDegreeCourseControl controller;

    private DegreeCourseBean selectedDegreeCourse;

    private Integer currentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO pass Student from login token
        controller = new EnrollToDegreeCourseControl();
        currentStage = 1;
        firstStageController.setDegreeCoursesList(controller.getDegreeCourses());
    }

    /**
     * Switch to the next stage of the use case
     */
    public void nextStage() {
        switch (currentStage) {
            case 1 -> goToStageTwo();
            case 2 -> goToStageThree();
        }
        currentStage += currentStage >= 3 ? 0 : 1;
    }

    /**
     * Switch to the previous stage of the use case
     */
    public void previousStage() {
        switch (currentStage) {
            case 2 -> secondStage.setVisible(false);
            case 3 -> thirdStage.setVisible(false);
        }
        currentStage -= currentStage <= 1 ? 0 : 1;
    }

    /**
     * Opens second use case stage
     */
    private void goToStageTwo() {
        selectedDegreeCourse = firstStageController.getSelectedDegreeCourse();
        secondStageController.setAnagraphicalData(controller.getStudentBean(), controller.getUserBean());
        secondStage.setVisible(true);
    }

    /**
     * Opens third use case stage
     */
    private void goToStageThree() {
        thirdStage.setVisible(true);
    }
}
