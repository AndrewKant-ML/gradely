package it.uniroma2.dicii.ispw.gradely;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadLoginPane(), 720, 512);
        stage.setTitle("Gradely");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        // TODO check GUI type
        System.setProperty("gradely.persistence_type", "DB");
        System.setProperty("gui", "javafx");

        launch(args);
    }

    private Pane loadLoginPane() {
        try {
            return FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource("login.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}