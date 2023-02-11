package it.uniroma2.dicii.ispw.gradely.use_cases.controllers_general.pending_event.graphic;

import it.uniroma2.dicii.ispw.gradely.beans_general.PendingEventBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PendingEventExamVerbalizationGraphicController implements Initializable {

    @FXML
    private Text text;
    private PendingEventBean pendingEvent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPendingEvent(PendingEventBean pendingEvent) {
        this.pendingEvent = pendingEvent;
        this.text.setText(this.pendingEvent.getType().message);
    }

    @FXML
    private void verbalizeExam() {
        // TODO implement exam verbalization
    }
}
