package it.uniroma2.dicii.ispw.gradely;

import it.uniroma2.dicii.ispw.gradely.session_manager.Token;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

public final class PageNavigationController {
    private static PageNavigationController INSTANCE;
    private BaseGraphicControl baseGraphicController;
    private Token sessionToken;

    private PageNavigationController() {

    }

    public static PageNavigationController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PageNavigationController();
        return INSTANCE;
    }

    public void setBaseGraphicController(BaseGraphicControl baseGraphicController) {
        this.baseGraphicController = baseGraphicController;
    }

    public void navigateTo(String pageName) {
        pageName = pageName.concat(".fxml");
        try {
            baseGraphicController.switchTo(
                    FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(pageName))));
        } catch (IOException e) {
            System.err.printf("Error while loading component %s. ", pageName);
            System.err.printf("Error message %s.\n", e.getMessage());
            e.printStackTrace();
            // TODO throw new exception
        }
    }

    public Token getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(Token sessionToken) {
        this.sessionToken = sessionToken;
    }

    // TODO implement user switch
}
