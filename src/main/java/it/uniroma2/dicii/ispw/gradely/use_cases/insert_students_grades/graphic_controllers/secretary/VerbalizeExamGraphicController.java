package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers.secretary;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ProtocolBean;
import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.graphic_controllers_general.homepages.ExamsTableController;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.InsertStudentsGradesSecretaryFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VerbalizeExamGraphicController implements Initializable {
    @FXML
    private AnchorPane examsTable;
    @FXML
    private ExamsTableController examsTableController;

    private InsertStudentsGradesSecretaryFacade facade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            facade = new InsertStudentsGradesSecretaryFacade(PageNavigationController.getInstance().getSessionTokenKey());
            examsTableController.setExamsData(facade.getVerbalizableExams(PageNavigationController.getInstance().getSessionTokenKey()));
        } catch (MissingAuthorizationException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message);
        }
    }

    @FXML
    private void verbalize() {
        ExamBean selectedExam = examsTableController.getSelectedExam();
        if (selectedExam == null)
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.MISSING_VALUE_TITLE.message, UserErrorMessagesEnum.SELECT_AN_EXAM_MSG.message);
        else {
            ProtocolBean protocolBean = new ProtocolBean(
                    selectedExam,
                    (int) (Math.random() * 10000000),
                    LocalDate.now()
            );
            try {
                facade.confirmExamVerbaleProtocolization(PageNavigationController.getInstance().getSessionTokenKey(), protocolBean);
                PageNavigationController.getInstance().returnToMainPage();
            } catch (MissingAuthorizationException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.AUTHORIZATION_TITLE.message, UserErrorMessagesEnum.MISSING_AUTHORIZATION_MSG.message);
            } catch (DAOException | PropertyException | ResourceNotFoundException | ObjectNotFoundException |
                     UserNotFoundException | WrongListQueryIdentifierValue | UnrecognizedRoleException |
                     WrongDegreeCourseCodeException e) {
                PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.DATA_RETRIEVAL_TITLE.message, UserErrorMessagesEnum.DATA_RETRIEVAL_MSG.message);
            }
        }
    }
}
