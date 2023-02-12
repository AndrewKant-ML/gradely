package it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.graphic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PendingEventSimpleGraphicController implements Initializable {

    @FXML
    private Text text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Automatically called after FXMLLoader::load() method for initialization
    }

    public void setMessage(String message) {
        text.setText(message);
    }
}
