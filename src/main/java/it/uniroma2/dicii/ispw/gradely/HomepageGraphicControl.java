package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageGraphicControl implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void openTaxesPage(){
        PageNavigationController.getInstance().navigateTo("taxes-view");
    }

    public void openCoursesPage(){
        PageNavigationController.getInstance().navigateTo("courses-view");
    }

    public void openExamsPage(){
        PageNavigationController.getInstance().navigateTo("exams-view");
    }

    public void openCareerPage(){
        PageNavigationController.getInstance().navigateTo("enroll_to_degree_course");
    }

    public void openServicesPage(){
        PageNavigationController.getInstance().navigateTo("services-view");
    }

    public void openPersonalDataPage(){
        PageNavigationController.getInstance().navigateTo("data-view");
    }
}
