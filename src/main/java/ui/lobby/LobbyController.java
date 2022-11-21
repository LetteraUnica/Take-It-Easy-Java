package ui.lobby;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import ui.mainMenu.MainMenu;

import javax.swing.text.html.ListView;

public class LobbyController {
    @FXML
    public ListView playerList;
    @FXML
    public void addPlayer() {
        // TODO: Add player to playerList
    }
    @FXML
    public void returnToMainMenu(ActionEvent e) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showStage((Stage) ((Node) e.getSource()).getScene().getWindow());
    }
    @FXML
    public void startGame() {
        // TODO: Implement startGame
    }
}
