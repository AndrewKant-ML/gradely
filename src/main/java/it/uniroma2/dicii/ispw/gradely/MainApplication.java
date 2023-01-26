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
        Scene scene = new Scene(loadBasePane(), 720, 512);
        stage.setTitle("Gradely");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Pane loadBasePane() {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MainApplication.class.getResource("base-view.fxml")));

        try {
            Pane basePane = loader.load();

            PageNavigationController.setBaseGraphicController(loader.getController());
            PageNavigationController.navigateTo("homepage");

            return basePane;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}