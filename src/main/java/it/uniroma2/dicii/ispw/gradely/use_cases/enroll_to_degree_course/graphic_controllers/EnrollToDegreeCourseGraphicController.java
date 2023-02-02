package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import it.uniroma2.dicii.ispw.gradely.enums.TestTypeEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.exceptions.TestRetrivialException;
import it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.EnrollToDegreeCourseStudentFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class EnrollToDegreeCourseGraphicController implements Initializable {
    @FXML
    public Parent fourthStage;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    @FXML
    private Parent firstStage;
    @FXML
    private Parent secondStage;
    @FXML
    private Parent thirdStage;
    @FXML
    private SelectDegreeCourseGraphicController firstStageController;
    @FXML
    private ConfirmAnagraphicalDataGraphicController secondStageController;
    @FXML
    private TestInfoGraphicController thirdStageController;
    @FXML
    private TestReservationCodeGraphicController fourthStageController;
    private EnrollToDegreeCourseStudentFacade facade;
    private DegreeCourseBean selectedDegreeCourse;
    private TestInfoBean testInfo;
    private Integer currentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            facade = new EnrollToDegreeCourseStudentFacade(PageNavigationController.getInstance().getSessionTokenKey());
            firstStageController.setDegreeCoursesList(facade.getDegreeCourses(PageNavigationController.getInstance().getSessionTokenKey()));
            currentStage = 1;
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message, e);
        } catch (DAOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message, e);
        }
    }

    /**
     * Switch to the next stage of the use case
     */
    public void nextStage(){
        switch (currentStage){
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
    public void previousStage(){
        switch (currentStage){
            case 2 -> {
                backButton.setVisible(false);
                secondStage.setVisible(false);
            }
            case 3 -> thirdStage.setVisible(false);
            case 4 -> {
                nextButton.setText("Next");
                fourthStage.setVisible(false);
            }
        }
        currentStage -= currentStage <= 1 ? 0 : 1;
    }

    /**
     * Opens second use case stage
     */
    private void goToStageTwo(){
        selectedDegreeCourse = firstStageController.getSelectedDegreeCourse();
        secondStageController.setAnagraphicalData(PageNavigationController.getInstance().getUserBean());
        secondStage.setVisible(true);
        backButton.setVisible(true);
    }

    /**
     * Opens third use case stage
     */
    private void goToStageThree(){
        try {
            this.testInfo = facade.getTestInfo(PageNavigationController.getInstance().getSessionTokenKey(), selectedDegreeCourse);
            thirdStageController.setData(testInfo, selectedDegreeCourse);
            nextButton.setText("Register");
            thirdStage.setVisible(true);
        } catch (TestRetrivialException e) {
            String msg = selectedDegreeCourse.getTestType().equals(TestTypeEnum.MUR) ? UserErrorMessagesEnum.MUR_TEST_RETRIEVAL_MSG.message : UserErrorMessagesEnum.MOODLE_TEST_RETRIEVAL_MSG.message;
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.EXTERNAL_ERROR_TITLE.message, msg);
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message);
        }
    }

    private void goToStageFour(){
        try {
            fourthStageController.setTestReservationCode(facade.reserveTest(PageNavigationController.getInstance().getSessionTokenKey(), testInfo).getReservationCode().toString());
            nextButton.setText("Close");
            fourthStage.setVisible(true);
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message);
        }
    }
}
