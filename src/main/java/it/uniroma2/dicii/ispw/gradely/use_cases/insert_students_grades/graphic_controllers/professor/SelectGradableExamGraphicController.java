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
    private List<ExamBean> examBeans = new ArrayList<>();
    private ExamBean selectedExam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjectCourse.setCellValueFactory(new PropertyValueFactory<>("subjectCourseName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appello.setCellValueFactory(new PropertyValueFactory<>("appello"));
        session.setCellValueFactory(new PropertyValueFactory<>("session"));
        gradableExamsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        gradableExamsTable.getColumns().addAll(List.of(subjectCourse, date, appello, session));
        gradableExamsTable.setOnMouseClicked(
                mouseEvent -> {
                    ExamBeanTableModel model = gradableExamsTable.getSelectionModel().getSelectedItem();
                    selectedExam = getBeanFromModel(model);
                }
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
                    examBean.getSession().toString().equals(modelSession) &&
                    examBean.getAppello().value == modelAppello &&
                    examBean.getDate().isEqual(modelDate))
                return examBean;
        }
        return null;
    }

    public void setExamsData(ExamListBean exams) {
        examBeans.addAll(exams.getExams());
        List<ExamBeanTableModel> models = new ArrayList<>();
        for (ExamBean examBean : examBeans)
            models.add(new ExamBeanTableModel(
                    examBean.getCourse().getName(),
                    examBean.getDate(),
                    examBean.getAppello(),
                    examBean.getSession()
            ));
        gradableExamsTable.setItems(FXCollections.observableArrayList(models));
    }

    public ExamBean getSelectedExam() {
        return selectedExam;
    }
}
