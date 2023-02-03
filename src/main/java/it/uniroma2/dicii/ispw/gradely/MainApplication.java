package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.enums.UserErrorMessagesEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Pane basePane = loadLoginPane();
        if (basePane != null) {
            Scene scene = new Scene(basePane, 720, 512);
            stage.setTitle("Gradely");
            stage.setScene(scene);
        }
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args){
        // TODO check GUI type
        System.setProperty("gradely.persistence_type", "DB");
        System.setProperty("gradely.front_end_type", "JAVAFX");

        launch(args);
    }

    private Pane loadLoginPane(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MainApplication.class.getResource("base_view.fxml")));
            Pane baseView = loader.load();
            PageNavigationController.getInstance().setBaseGraphicController(loader.getController());
            PageNavigationController.getInstance().navigateTo("login");
            return baseView;
        } catch (IOException e) {
            PageNavigationController.getInstance().showAlert(Alert.AlertType.ERROR, UserErrorMessagesEnum.RESOURCE_LOADING_TITLE.message, UserErrorMessagesEnum.ROLE_ERROR_MSG.message, e);
            return null;
        }
    }
}