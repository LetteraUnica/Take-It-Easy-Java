package ui.mainGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;

import static ui.Utils.getStage;

public class MainGameController {
    @FXML
    private Pane boardPane;
    @FXML
    private GridPane playerList;
    @FXML
    private Button returnToLobbyButton;
    @FXML
    private Button placeTileButton;

    public void returnToLobby(ActionEvent e) throws IOException {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.LOBBY_FXML);
    }
}
