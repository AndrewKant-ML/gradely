package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageGraphicController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Called by FxmlLoader::load() after loading homepage view
    }

    public void openCareerPage() {
        PageNavigationController.getInstance().navigateTo("enroll_to_degree_course");
    }

    public void openTaxesPage() {
        PageNavigationController.getInstance().navigateTo("under_construction");
    }

    public void openCoursesPage() {
        PageNavigationController.getInstance().navigateTo("under_construction");
    }

    public void openExamsPage() {
        PageNavigationController.getInstance().navigateTo("under_construction");
    }

    public void openServicesPage() {
        PageNavigationController.getInstance().navigateTo("under_construction");
    }

    public void openPersonalDataPage() {
        PageNavigationController.getInstance().navigateTo("under_construction");
    }
}
