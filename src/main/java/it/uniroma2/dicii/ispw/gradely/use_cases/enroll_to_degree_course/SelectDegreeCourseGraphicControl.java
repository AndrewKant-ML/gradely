package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.general_beans.DegreeCourseBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectDegreeCourseGraphicControl implements Initializable {

    @FXML
    private ListView<DegreeCourseBean> degreeCoursesList;

    private DegreeCourseBean chosenDegreeCourse = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO remove stub

        degreeCoursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        degreeCoursesList.setOnMouseClicked(mouseEvent -> chosenDegreeCourse = degreeCoursesList.getSelectionModel().getSelectedItem());
    }

    public DegreeCourseBean getSelectedDegreeCourse() {
        return chosenDegreeCourse;
    }

    public void setDegreeCoursesList(List<DegreeCourseBean> degreeCoursesList) {
        this.degreeCoursesList.setItems(FXCollections.observableArrayList(degreeCoursesList));
    }
}
