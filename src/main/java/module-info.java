module it.uniroma2.dicii.ispw.gradely {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.uniroma2.dicii.ispw.gradely to javafx.fxml;
    exports it.uniroma2.dicii.ispw.gradely;
    exports it.uniroma2.dicii.ispw.gradely.session_manager;
    exports it.uniroma2.dicii.ispw.gradely.model;
    opens it.uniroma2.dicii.ispw.gradely.session_manager to javafx.fxml;
}