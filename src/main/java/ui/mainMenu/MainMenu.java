package ui.mainMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {

    public static final String MAIN_MENU_CSS = "main_menu.css";
    public static final String STARTING_MENU_FXML = "Starting_menu.fxml";

    public Scene initializeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(STARTING_MENU_FXML));
        Scene scene = new Scene(root);
        String css = getClass().getResource(MAIN_MENU_CSS).toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    public void showStage(Stage stage) throws Exception {
        Scene scene = initializeScene();
        stage.setScene(scene);
        stage.show();
    }
}
