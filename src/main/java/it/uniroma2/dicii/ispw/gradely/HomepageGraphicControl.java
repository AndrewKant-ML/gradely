package it.uniroma2.dicii.ispw.gradely;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageGraphicControl extends AbstractGraphicControl implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openTaxesPage() {
        parentControl.switchTo("taxes-view");
    }

    public void openCoursesPage() {
        parentControl.switchTo("course-view");
    }

    public void openExamsPage() {
        parentControl.switchTo("exams-view");
    }

    public void openCareerPage() {
        parentControl.switchTo("career-view");
    }

    public void openServicesPage() {
        parentControl.switchTo("services-view");
    }

    public void openPersonalDataPage() {
        parentControl.switchTo("data-view");
    }
}
