package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    public void exitGame(ActionEvent e) {
        Platform.exit();
    }
    @FXML
    public void howToPlay(ActionEvent e) {
        // TODO: Add how to play
    }
    @FXML
    public void startGame(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        String css = getClass().getResource("lobby.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
    }
}
