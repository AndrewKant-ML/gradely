package it.uniroma2.dicii.ispw.gradely;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

public class PageNavigationController {

    private static BaseGraphicControl baseGraphicController;

    public static void setBaseGraphicController(BaseGraphicControl baseGraphicController) {
        PageNavigationController.baseGraphicController = baseGraphicController;
    }

    public static void navigateTo(String pageName) {
        pageName = pageName.concat(".fxml");
        try {
            baseGraphicController.switchTo(
                    FXMLLoader.load(
                            Objects.requireNonNull(MainApplication.class.getResource(pageName))
                    )
            );
        } catch (IOException e) {
            System.err.printf("Error while loading component %s. ", pageName);
            System.err.printf("Error message %s.\n", e.getMessage());
            e.printStackTrace();
            // TODO throw new exception
        }
    }
}
