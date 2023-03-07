package ui.navigator;

import engine.controller.GameController;
import engine.controller.GameControllerInterface;
import exceptions.TileCacheEmptyException;
import exceptions.ReassignedControllerException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.windows.UIControllerInterface;

import java.io.IOException;

public class Navigator {

    private static void injectGameController(GameControllerInterface gameController, FXMLLoader loader) throws ReassignedControllerException {
        UIControllerInterface uiControllerInterface = loader.getController();
        uiControllerInterface.initController(gameController);
    }

    public void navigateTo(Stage stage, String to) throws IOException {
        try {
            navigateWithController(stage, to, new GameController());
        } catch (ReassignedControllerException | TileCacheEmptyException ignored) {
        }
    }

    public void navigateWithController(Stage stage, String to, GameControllerInterface gameController) throws IOException, ReassignedControllerException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(to));
        Parent root = loader.load();
        if (gameController != null) {
            injectGameController(gameController, loader);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
