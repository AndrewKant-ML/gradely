package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.UserNotFoundException;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesProfessorFacade;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers.professor.SelectGradableExamGraphicController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertStudentsGradesProfessorGraphicControl implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    @FXML
    private Parent firstStage;
    @FXML
    private Parent secondStage;
    @FXML
    private SelectGradableExamGraphicController firstStageController;
    private InsertStudentsGradesProfessorFacade facade;
    private Integer currentStage;

    private ExamBean selectedExam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            facade = new InsertStudentsGradesProfessorFacade(PageNavigationController.getInstance().getSessionTokenKey());
            currentStage = 1;
            firstStageController.setExamsData(facade.getGradableExams(PageNavigationController.getInstance().getSessionTokenKey()));
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message);
        } catch (DAOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message);
        } catch (UserNotFoundException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.USER_NOT_FOUND_MSG.message);
        }
    }

    /**
     * Switch to the next stage of the use case
     */
    public void nextStage() {
        switch (currentStage) {
            case 1 -> goToStageTwo();
            case 2 -> goToStageThree();
            case 3 -> goToStageFour();
            case 4 -> PageNavigationController.getInstance().returnToMainPage();
        }
        currentStage += currentStage >= 4 ? 0 : 1;
    }

    /**
     * Switch to the previous stage of the use case
     */
    public void previousStage() {
        switch (currentStage) {
            case 2 -> {
                backButton.setVisible(false);
                secondStage.setVisible(false);
            }
            case 3 -> thirdStage.setVisible(false);
            case 4 -> {
                backButton.setVisible(true);
                nextButton.setText("Next");
                fourthStage.setVisible(false);
            }
        }
        currentStage -= currentStage <= 1 ? 0 : 1;
    }

    private void goToStageTwo() {
        selectedExam = firstStageController.getSelectedExam();
        if (selectedExam == null)
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.MISSING_VALUE.message, UserErrorMessagesEnum.SELECT_AN_EXAM_MSG.message);
        else {
            // TODO implement
        }
    }
}
