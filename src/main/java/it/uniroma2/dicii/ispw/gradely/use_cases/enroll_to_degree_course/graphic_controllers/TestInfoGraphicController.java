package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.beans_general.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.beans_general.TestInfoBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TestInfoGraphicController implements Initializable {


    @FXML
    private Label degreeCourseName;
    @FXML
    private Label testID;
    @FXML
    private Label testDate;
    @FXML
    private Label testPlace;

    @FXML
    private Hyperlink testInfoUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        // Automatically called after FXMLLoader::load() method for initialization
    }

    public void setData(TestInfoBean testInfo, DegreeCourseBean degreeCourse){
        this.degreeCourseName.setText(degreeCourse.toString());
        this.testID.setText(testInfo.getId().toString());
        this.testDate.setText(testInfo.getTestDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.testPlace.setText(testInfo.getPlace());
        this.testInfoUrl.setText(testInfo.getInfoLink().toString());
    }
}
