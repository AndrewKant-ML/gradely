package it.uniroma2.dicii.ispw.gradely.graphic_controllers_general.homepages;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomepageGraphicController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Called by FxmlLoader::load() after loading homepage view
    }

    @FXML
    private void openCareerPage() {
        PageNavigationController.getInstance().navigateTo("career");
    }

    @FXML
    private void underConstructionPageStudent() {
        PageNavigationController.getInstance().navigateToUnderConstructionPage();
    }
}
