package ui.lobby;

import engine.controller.GameInterface;
import exceptions.ReassignedControllerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import ui.UIControllerInterface;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;

import static utils.ui.UIUtils.getStage;

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
    }

    @FXML
    public void addPlayer() {
        String playerName = playerNameField.getText();
        gameController.addPlayer(playerName);

        Pane rowContainer = createRowContainer();
        addPlayerName(playerName, rowContainer);
        addRemoveButton(playerName, rowContainer);
        playerListPane.addRow(playerListPane.getRowCount(), rowContainer);

        playerNameField.clear();
        startMatchButtonEnable();
        addPlayerButtonEnable();
    }

    private void addRemoveButton(String playerName, Pane rowContainer) {
        Button removePlayerButton = new Button("Remove");
        rowContainer.getChildren().add(removePlayerButton);
        removePlayerButton.setStyle("-fx-font-size:14");
        removePlayerButton.relocate(180, 0);
        removePlayerButton.setOnAction(event -> {
            gameController.removePlayer(gameController.getNicknames().indexOf(playerName));
            playerListPane.getChildren().remove(rowContainer);
            startMatchButtonEnable();
        });
    }

    private static void addPlayerName(String playerName, Pane rowContainer) {
        Label playerNameText = new Label(playerName);
        rowContainer.getChildren().add(playerNameText);
        playerNameText.setFont(Font.font(20));
        playerNameText.setMaxWidth(140);
        playerNameText.setTextOverrun(OverrunStyle.ELLIPSIS);
        playerNameText.relocate(0, 0);
    }

    private static Pane createRowContainer() {
        Pane rowContainer = new Pane();
        rowContainer.setPadding(new javafx.geometry.Insets(20, 0, 20, 0));
        rowContainer.setPrefHeight(30);
        return rowContainer;
    }

    @FXML
    public void returnToMainMenu(ActionEvent e) throws Exception {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.STARTING_MENU_FXML);
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
