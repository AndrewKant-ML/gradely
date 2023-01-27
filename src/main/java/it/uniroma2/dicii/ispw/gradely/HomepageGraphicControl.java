package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageGraphicControl implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openTaxesPage() {
        PageNavigationController.navigateTo("taxes-view");
    }

    public void openCoursesPage() {
        PageNavigationController.navigateTo("courses-view");
    }

    public void openExamsPage() {
        PageNavigationController.navigateTo("exams-view");
    }

    public void openCareerPage() {
        PageNavigationController.navigateTo("degree-course-enrollment-view");
    }

    public void openServicesPage() {
        PageNavigationController.navigateTo("services-view");
    }

    public void openPersonalDataPage() {
        PageNavigationController.navigateTo("data-view");
    }
}
