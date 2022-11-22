package ui.lobby;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.navigator.Navigator;
import ui.navigator.NavigationConstants;

import java.util.ArrayList;

public class LobbyController {
    @FXML
    public Button addPlayerButton;
    @FXML
    private GridPane playerList;
    @FXML
    private TextField playerNameField;
    @FXML
    private Button startMatchButton;

    private final ArrayList<String> playerNames = new ArrayList<>();
    @FXML
    public void addPlayer() {
        String playerName = playerNameField.getText();
        if (playerName.isEmpty()) {
            return;
        }
        playerNames.add(playerName);

        Pane rowContainer = new Pane();
        rowContainer.setPadding(new javafx.geometry.Insets(20, 0, 20, 0));
        rowContainer.setPrefHeight(30);

        Label playerNameText = new Label(playerName);
        playerNameText.setFont(Font.font(20));
        playerNameText.setMaxWidth(140);
        playerNameText.setTextOverrun(OverrunStyle.ELLIPSIS);
        rowContainer.getChildren().add(playerNameText);
        playerNameText.relocate(0, 0);

        Button removePlayerButton = new Button("Remove");
        removePlayerButton.setStyle("-fx-font-size:14");
        rowContainer.getChildren().add(removePlayerButton);
        removePlayerButton.relocate(180, 0);

        removePlayerButton.setOnAction(event -> {
            playerNames.remove(playerName);
            playerList.getChildren().remove(rowContainer);
            checkStartMatchButtonEnabled();
        });

        playerList.addRow(playerList.getRowCount(), rowContainer);

        checkStartMatchButtonEnabled();
    }

    private void checkStartMatchButtonEnabled() {
        startMatchButton.setDisable(playerNames.isEmpty());
    }

    @FXML
    public void returnToMainMenu(ActionEvent e) throws Exception {
        Navigator navigator = new Navigator();
        navigator.navigateTo((Stage) ((Node) e.getSource()).getScene().getWindow(), NavigationConstants.STARTING_MENU_FXML);
    }
    @FXML
    public void startMatch() {
        // TODO: Implement startGame
    }

    @FXML
    public void onPlayerNameChange(KeyEvent e) {
        addPlayerButton.setDisable(playerNameField.getText().isEmpty());
    }
}
