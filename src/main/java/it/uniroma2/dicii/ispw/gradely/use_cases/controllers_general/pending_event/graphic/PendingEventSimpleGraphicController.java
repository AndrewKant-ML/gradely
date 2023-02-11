package it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.graphic;

import it.uniroma2.dicii.ispw.gradely.beans_general.PendingEventBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PendingEventSimpleGraphicController implements Initializable {

    @FXML
    private Label text;

    private PendingEventBean pendingEvent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPendingEvent(PendingEventBean pendingEvent) {
        this.pendingEvent = pendingEvent;
        text.setText(pendingEvent.getType().message);
    }
}
