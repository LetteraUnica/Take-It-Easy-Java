package ui.mainMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    public Scene initializeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Starting_menu.fxml"));
        Scene scene = new Scene(root);
        String css = getClass().getResource("main_menu.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    public void showStage(Stage stage) throws Exception {
        Scene scene = initializeScene();
        stage.setScene(scene);
        stage.show();
    }
}
