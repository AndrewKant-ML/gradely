package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers.professor;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamEnrollmentListBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamResultBean;
import it.uniroma2.dicii.ispw.gradely.enums.ResultEnum;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.ExamResultBeanTableModel;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.StudentGradeBean;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InsertResultsAndGradesGraphicController implements Initializable {
    @FXML
    public TableView<ExamResultBeanTableModel> enrollmentsTable;
    @FXML
    public TableColumn<ExamResultBeanTableModel, String> studentName;
    @FXML
    public TableColumn<ExamResultBeanTableModel, String> studentMatricola;
    @FXML
    public TableColumn<ExamResultBeanTableModel, ChoiceBox<ResultEnum>> result;
    @FXML
    public TableColumn<ExamResultBeanTableModel, String> grade;
    private List<ExamEnrollmentBean> enrollmentBeans = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        this.studentMatricola.setCellValueFactory(new PropertyValueFactory<>("studentMatricola"));
        this.result.setCellValueFactory(new PropertyValueFactory<>("result"));
        this.grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }

    private ChoiceBox<ResultEnum> buildChoiceBox() {
        ChoiceBox<ResultEnum> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(
                ResultEnum.PROMOSSO,
                ResultEnum.RESPINTO,
                ResultEnum.ASSENTE,
                ResultEnum.RITIRATO
        );
        choiceBox.getSelectionModel().select(ResultEnum.RITIRATO);
        return choiceBox;
    }

    public void setEnrollments(ExamEnrollmentListBean listBean) {
        this.enrollmentBeans.addAll(listBean.getExamEnrollmentBeans());
        List<ExamResultBeanTableModel> resultBeanTableModels = new ArrayList<>();
        for (ExamEnrollmentBean enrollmentBean : enrollmentBeans) {
            resultBeanTableModels.add(new ExamResultBeanTableModel(
                    String.format("%s %s", enrollmentBean.getStudent().getUser().getName(), enrollmentBean.getStudent().getUser().getSurname()),
                    enrollmentBean.getStudent().getMatricola(),
                    buildChoiceBox()
            ));
        }
        enrollmentsTable.setItems(FXCollections.observableArrayList(resultBeanTableModels));
    }

    /**
     * Builds and returns an array of StudentGradeBeans, by coupling each enrollment
     * to the inserted grade.
     *
     * @return a list of StudentGradeBeans
     */
    public List<StudentGradeBean> getResults() {
        List<StudentGradeBean> gradeBeans = new ArrayList<>();
        for (ExamEnrollmentBean enrollmentBean : this.enrollmentBeans)
            for (ExamResultBeanTableModel item : enrollmentsTable.getItems())
                if (enrollmentBean.getStudentMatricola().equals(item.getStudentMatricolaProperty()))
                    gradeBeans.add(new StudentGradeBean(
                            enrollmentBean,
                            new ExamResultBean(
                                    item.getGradeProperty(),
                                    item.getResultProperty().getValue(),
                                    false
                            )
                    ));
        return gradeBeans;
    }
}
