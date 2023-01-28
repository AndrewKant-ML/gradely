package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course;

import it.uniroma2.dicii.ispw.gradely.general_beans.DegreeCourseBean;
import it.uniroma2.dicii.ispw.gradely.general_beans.TestInfoBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestInfoController implements Initializable {


    @FXML
    private Label degreeCourseName, testID, testDate, testPlace;

    @FXML
    private Hyperlink testInfoUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(TestInfoBean testInfo, DegreeCourseBean degreeCourse) {
        this.degreeCourseName.setText(degreeCourse.getName());
        this.testID.setText(testInfo.getID());
        this.testDate.setText(testInfo.getTestDate().format(new DateTimeFormatterBuilder().toFormatter(Locale.ITALIAN)));
        this.testPlace.setText(testInfo.getPlace());
        this.testInfoUrl.setText(testInfo.getInfoLink().getPath());
    }
}
