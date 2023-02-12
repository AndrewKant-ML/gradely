package it.uniroma2.dicii.ispw.gradely.graphic_controllers_general.homepages;

import it.uniroma2.dicii.ispw.gradely.PageNavigationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SecretaryHomepageGraphicController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Automatically called after FXMLLoader::load() method for initialization
    }

    @FXML
    private void underConstructionPageSecretary() {
        PageNavigationController.getInstance().navigateToUnderConstructionPage();
    }
}
