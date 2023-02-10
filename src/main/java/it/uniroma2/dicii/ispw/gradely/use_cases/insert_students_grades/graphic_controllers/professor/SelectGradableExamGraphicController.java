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
    public TableColumn<ExamBeanTableModel, String> subjectCourse;
    @FXML
    public TableColumn<ExamBeanTableModel, LocalDate> date;
    @FXML
    public TableColumn<ExamBeanTableModel, Integer> appello;
    @FXML
    public TableColumn<ExamBeanTableModel, String> session;
    @FXML
    private TableView<ExamBeanTableModel> gradableExamsTable;
    private List<ExamBean> examBeans;
    private ExamBean selectedExam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjectCourse.setCellValueFactory(new PropertyValueFactory<>("subjectCourseName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setCellValueFactory(new PropertyValueFactory<>("appello"));
        session.setCellValueFactory(new PropertyValueFactory<>("session"));
        gradableExamsTable.getColumns().addAll(List.of(subjectCourse, date, appello, session));
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
            modelName = model.getSubjectCourseNameProperty();
            modelDate = LocalDate.parse(model.getDateProperty());
            modelAppello = model.getAppelloProperty();
            modelSession = model.getSessionProperty();
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
