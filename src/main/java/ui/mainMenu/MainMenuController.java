package ui.mainMenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import ui.lobby.Lobby;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainMenuController {

    public static final String HOW_TO_PLAY_URL = "https://www.ultraboardgames.com/take-it-easy/game-rules.php";

    @FXML
    public void exitGame(ActionEvent e) {
        Platform.exit();
    }
    @FXML
    public void howToPlay(ActionEvent e) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(HOW_TO_PLAY_URL));
    }
    @FXML
    public void startGame(ActionEvent e) throws Exception {
        Lobby lobby = new Lobby();
        lobby.showStage((Stage) ((Node) e.getSource()).getScene().getWindow());
    }
}
