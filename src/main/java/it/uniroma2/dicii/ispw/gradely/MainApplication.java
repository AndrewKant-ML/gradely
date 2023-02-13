package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.FrontEndTypeEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.PropertyException;
import it.uniroma2.dicii.ispw.gradely.exceptions.ResourceNotFoundException;
import it.uniroma2.dicii.ispw.gradely.loggers_general.GeneralLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApplication extends Application {
//test ci analysis
    private static final Logger LOGGER = Logger.getLogger(MainApplication.class.getName());

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

    public static void main(String[] args) {
        try {
            FrontEndTypeEnum frontEndType = FrontEndTypeEnum.getFrontEndTypeByValue(PropertiesHandler.getInstance().getProperty("front_end_type"));
            if (frontEndType != null)
                switch (frontEndType) {
                    case JAVAFX -> launch(args);
                    case CLI -> {
                        launchCLI();
                    }
                    default -> throw new PropertyException(ExceptionMessagesEnum.UNEXPECTED_PROPERTY_NAME.message);
                }
            else
                throw new PropertyException(ExceptionMessagesEnum.UNEXPECTED_PROPERTY_NAME.message);
        } catch (ResourceNotFoundException | PropertyException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private static void launchCLI() {
        // To be implemented
    }

    private Pane loadLoginPane() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(MainApplication.class.getResource("base_view.fxml")));
            Pane baseView = loader.load();
            PageNavigationController.getInstance().setBaseGraphicController(loader.getController());
            PageNavigationController.getInstance().navigateTo("login");
            return baseView;
        } catch (IOException e) {
            GeneralLogger.logSevere(e.getMessage());
            return null;
        }
    }
}