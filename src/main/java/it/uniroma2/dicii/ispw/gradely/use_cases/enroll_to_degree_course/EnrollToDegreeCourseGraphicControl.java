package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EnrollToDegreeCourseGraphicControl implements Initializable {

    @FXML
    private ListView<String> degreeCoursesList;

    private EnrollToDegreeCourseControl controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new EnrollToDegreeCourseControl();
        ObservableList<String> values = FXCollections.observableArrayList();
        values.addAll("Ingegneria informatica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica",
                "Ingegneria meccanica"
        );
        degreeCoursesList.setItems(values);

        degreeCoursesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(degreeCoursesList.getSelectionModel().getSelectedItem());
            }
        });
    }
}
