package it.uniroma2.dicii.ispw.gradely;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("base-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 512);
        stage.setTitle("Gradely");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void loadFxml(String viewName) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(viewName));
    }
}