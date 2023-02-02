package it.uniroma2.dicii.ispw.gradely.use_cases.insert_students_grades.graphic_controllers;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorHomepageGraphicController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openExamsPage() {
        PageNavigationController.getInstance().navigateTo("exams-view");
    }

    public void openCoursesPage() {
        PageNavigationController.getInstance().navigateTo("courses-view");
    }

    public void openReceptionsPage() {
        PageNavigationController.getInstance().navigateTo("receptions-view");
    }

    public void openCareerPage() {
        PageNavigationController.getInstance().navigateTo("career-view");
    }

    public void openServicesPage() {
        PageNavigationController.getInstance().navigateTo("services-view");
    }

    public void openPersonalDataPage() {
        PageNavigationController.getInstance().navigateTo("data-view");
    }
}
