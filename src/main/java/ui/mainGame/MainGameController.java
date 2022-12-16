package ui.mainGame;

import engine.controller.GameInterface;
import exceptions.ReassignedControllerExeption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ui.UIControllerInterface;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;

import static ui.Utils.getStage;

public class MainGameController implements UIControllerInterface {
    @FXML
    private Pane boardPane;
    @FXML
    private GridPane playerList;
    @FXML
    private Button returnToLobbyButton;
    @FXML
    private Button placeTileButton;

    private GameInterface gameController;

    @Override
    public void initController(GameInterface gameController) {
        this.gameController = gameController;

        initializePlayerListPane();
        initializeBoard();
    }

    private void initializePlayerListPane() {
    }

    private void initializeBoard() {
    }

    @FXML
    public void returnToLobby(ActionEvent e) throws IOException, ReassignedControllerExeption {
        Navigator navigator = new Navigator();
        navigator.navigateWithController(getStage(e), NavigationConstants.LOBBY_FXML, gameController);
    }
}
