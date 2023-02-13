package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesProfessorFacade;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeListBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers.professor.InsertResultsAndGradesGraphicController;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers.professor.SelectGradableExamGraphicController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.List;
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
    @FXML
    private InsertResultsAndGradesGraphicController secondStageController;
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
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message, e);
        } catch (DAOException | UnrecognizedRoleException | WrongDegreeCourseCodeException |
                 WrongListQueryIdentifierValue e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message, e);
        } catch (UserNotFoundException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.USER_NOT_FOUND_MSG.message, e);
        }
    }

    /**
     * Switch to the next stage of the use case
     */
    public void nextStage() {
        if (currentStage == 1)
            goToInsertResultsAndGradesPage();
        else if (currentStage == 2)
            saveExamResults();
    }

    /**
     * Switch to the previous stage of the use case
     */
    public void previousStage() {
        if (currentStage == 2) {
            backButton.setVisible(false);
            secondStage.setVisible(false);
        }
        currentStage -= currentStage <= 1 ? 0 : 1;
    }

    private void updateCurrentStage() {
        currentStage += currentStage >= 2 ? 0 : 1;
    }

    private void goToInsertResultsAndGradesPage() {
        selectedExam = firstStageController.getSelectedExam();
        if (selectedExam == null)
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.MISSING_VALUE_TITLE.message, UserErrorMessagesEnum.SELECT_AN_EXAM_MSG.message);
        else {
            try {
                ExamEnrollmentListBean enrollments = facade.getExamEnrollments(PageNavigationController.getInstance().getSessionTokenKey(), selectedExam);
                if (enrollments.getExamEnrollmentBeans().isEmpty()) // TBI send notification to segreteria if there are no enrollments
                    PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.MISSING_VALUE_TITLE.message, UserErrorMessagesEnum.NO_ENROLLMENTS_MSG.message);
                else {
                    secondStageController.setEnrollments(enrollments);
                    backButton.setVisible(true);
                    nextButton.setText("Save");
                    updateCurrentStage();
                    secondStage.setVisible(true);
                }
            } catch (MissingAuthorizationException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message, e);
            } catch (DAOException | WrongListQueryIdentifierValue e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message, e);
            } catch (PropertyException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.PROPERTY_VALUE_TITLE.message, UserErrorMessagesEnum.PROPERTY_VALUE_MSG.message, e);
            } catch (ResourceNotFoundException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message, e);
            } catch (UserNotFoundException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.USER_NOT_FOUND_MSG.message, e);
            } catch (UnrecognizedRoleException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.ROLE_ERROR_TITLE.message, UserErrorMessagesEnum.ROLE_ERROR_MSG.message, e);
            } catch (ObjectNotFoundException | WrongDegreeCourseCodeException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.OBJ_NOT_FOUND_TITLE.message, UserErrorMessagesEnum.OBJ_NOT_FOUND_MSG.message, e);
            }
        }
    }

    private void saveExamResults() {
        try {
            List<StudentGradeBean> gradeBeans = secondStageController.getResults();
            facade.saveExamResults(PageNavigationController.getInstance().getSessionTokenKey(), new StudentGradeListBean(gradeBeans, selectedExam));
            PageNavigationController.getInstance().returnToMainPage();
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message, e);
        } catch (DAOException | WrongListQueryIdentifierValue | WrongTimerTypeException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message, e);
        } catch (PropertyException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.PROPERTY_VALUE_TITLE.message, UserErrorMessagesEnum.PROPERTY_VALUE_MSG.message, e);
        } catch (ResourceNotFoundException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.RESOURCE_LOADING_MSG.message, e);
        } catch (UserNotFoundException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.USER_NOT_FOUND_MSG.message, e);
        } catch (UnrecognizedRoleException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.ROLE_ERROR_TITLE.message, UserErrorMessagesEnum.ROLE_ERROR_MSG.message, e);
        } catch (ObjectNotFoundException | WrongDegreeCourseCodeException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.OBJ_NOT_FOUND_TITLE.message, UserErrorMessagesEnum.OBJ_NOT_FOUND_MSG.message, e);
        }
    }
}
