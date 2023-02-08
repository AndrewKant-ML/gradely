package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers.professor;

import it.uniroma2.dicii.ispw.gradely.beans_general.ExamBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.ExamListBean;
import it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.beans.ExamBeanTableModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectGradableExamGraphicController implements Initializable {

    @FXML
    private TableView<ExamBeanTableModel> gradableExamsTable;

    private List<ExamBean> examBeans;
    private ExamBean selectedExam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<ExamBeanTableModel, String> subjectCourseColumn = new TableColumn<>("Subject course");
        subjectCourseColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCourseName"));
        TableColumn<ExamBeanTableModel, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<ExamBeanTableModel, Integer> appelloColumn = new TableColumn<>("Appello");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appello"));
        TableColumn<ExamBeanTableModel, String> sessionColumn = new TableColumn<>("Session");
        sessionColumn.setCellValueFactory(new PropertyValueFactory<>("session"));
        gradableExamsTable.getColumns().addAll(subjectCourseColumn, dateColumn, appelloColumn, sessionColumn);
        gradableExamsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        gradableExamsTable.setOnMouseClicked(
                mouseEvent -> selectedExam = getBeanFromModel(gradableExamsTable.getSelectionModel().getSelectedItem())
        );
    }

    private ExamBean getBeanFromModel(ExamBeanTableModel model) {
        String modelName;
        LocalDate modelDate;
        int modelAppello;
        String modelSession;
        for (ExamBean examBean : examBeans) {
            modelName = model.getSubjectCourseName();
            modelDate = LocalDate.parse(model.getDate());
            modelAppello = model.getAppello();
            modelSession = model.getSession();
            if (examBean.getCourse().getName().equals(modelName) &&
                    examBean.getSessione().toString().equals(modelSession) &&
                    Objects.equals(examBean.getAppello().value, modelAppello) &&
                    examBean.getDate().isEqual(modelDate))
                return examBean;
        }
        return null;
    }

    public void setExamsData(ExamListBean exams) {
        List<ExamBeanTableModel> models = new ArrayList<>();
        for (ExamBean examBean : exams.getExams())
            models.add(new ExamBeanTableModel(
                    examBean.getCourse().getName(),
                    LocalDate.now(),
                    examBean.getAppello(),
                    examBean.getSessione()
            ));
        gradableExamsTable.setItems(FXCollections.observableArrayList(models));
    }

    public ExamBean getSelectedExam() {
        return selectedExam;
    }
}
