package ui.lobby;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lobby {
    public static final String LOBBY_CSS = "lobby.css";
    public static final String LOBBY_FXML = "lobby.fxml";

    public Scene initializeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(LOBBY_FXML));
        Scene scene = new Scene(root);
        String css = getClass().getResource(LOBBY_CSS).toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    public void showStage(Stage stage) throws Exception {
        Scene scene = initializeScene();
        stage.setScene(scene);
        stage.show();
    }
}
