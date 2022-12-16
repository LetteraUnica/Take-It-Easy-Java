package ui.navigator;

import engine.controller.GameInterface;
import exceptions.ReassignedControllerExeption;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UIControllerInterface;

import java.io.IOException;
import java.util.Objects;

public class Navigator {

    public void navigateTo(Stage stage, String to) throws IOException {
        try {
            navigateWithController(stage, to, null);
        } catch (ReassignedControllerExeption ignored) {
        }
    }

    public void navigateWithController(Stage stage, String to, GameInterface gameController) throws IOException, ReassignedControllerExeption {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(to));
        Parent root = loader.load();
        if (gameController != null) {
            injectGameController(gameController, loader);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void injectGameController(GameInterface gameController, FXMLLoader loader) throws ReassignedControllerExeption {
        UIControllerInterface iuControllerInterface = loader.getController();
        iuControllerInterface.initController(gameController);
    }
}
