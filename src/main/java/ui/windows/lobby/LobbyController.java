package ui.windows.lobby;

import engine.controller.GameInterface;
import exceptions.ReassignedControllerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ui.windows.UIControllerInterface;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;
import ui.utils.UIUtils;

import java.io.IOException;

import static ui.utils.UIUtils.getStage;

public class LobbyController implements UIControllerInterface {
    @FXML
    public Button addPlayerButton;
    @FXML
    private GridPane playerListPane;
    @FXML
    private TextField playerNameField;
    @FXML
    private Button startMatchButton;

    private GameInterface gameController;

    @Override
    public void initController(GameInterface gameController) throws ReassignedControllerException {
        if (this.gameController != null) {
            throw new ReassignedControllerException();
        }
        this.gameController = gameController;

        initializePlayerList();
    }

    private void initializePlayerList() {
        for (String playerName : gameController.getNicknames()) {
            addToPlayerList(playerName);
        }
        startMatchButtonEnable();
    }

    @FXML
    public void addPlayerButtonPressed() {
        String playerName = playerNameField.getText();
        gameController.addPlayer(playerName);
        addToPlayerList(playerName);

        playerNameField.clear();
        startMatchButtonEnable();
        addPlayerButtonEnable();
    }

    private void addToPlayerList(String playerName) {
        Pane rowContainer = UIUtils.createRowContainer();
        UIUtils.addPlayerName(playerName, rowContainer, 140, 16);
        addRemoveButton(playerName, rowContainer);
        playerListPane.addRow(playerListPane.getRowCount(), rowContainer);
    }

    private void addRemoveButton(String playerName, Pane rowContainer) {
        Button removePlayerButton = new Button("Remove");
        rowContainer.getChildren().add(removePlayerButton);
        removePlayerButton.setStyle("-fx-font-size:14");
        removePlayerButton.relocate(180, 0);
        removePlayerButton.setOnAction(event -> {
            gameController.removePlayer(playerName);
            playerListPane.getChildren().remove(rowContainer);
            startMatchButtonEnable();
        });
    }

    @FXML
    public void returnToMainMenu(ActionEvent e) throws IOException {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.MAIN_MENU_FXML);
    }

    @FXML
    public void startMatch(ActionEvent e) throws IOException, ReassignedControllerException {
        Navigator navigator = new Navigator();
        navigator.navigateWithController(getStage(e), NavigationConstants.MAIN_GAME_FXML, gameController);
    }

    @FXML
    public void onPlayerNameChange() {
        addPlayerButtonEnable();
    }

    private void startMatchButtonEnable() {
        startMatchButton.setDisable(gameController.getNicknames().isEmpty());
    }

    private void addPlayerButtonEnable() {
        addPlayerButton.setDisable(playerNameField.getText().isEmpty() || gameController.getNicknames().contains(playerNameField.getText()));
    }
}
