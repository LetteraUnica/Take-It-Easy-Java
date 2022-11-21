package ui.mainMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Window;

import java.io.IOException;

public class MainMenu extends Application implements Window {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = initializeScene();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public Scene initializeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Starting_menu.fxml"));
        Scene scene = new Scene(root);
        String css = getClass().getResource("main_menu.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }
}
