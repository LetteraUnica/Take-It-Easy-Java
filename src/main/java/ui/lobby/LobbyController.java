package ui.lobby;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.mainMenu.MainMenu;

import java.util.HashSet;

public class LobbyController {
    private HashSet<String> currentPlayers = new HashSet<>();
    @FXML
    private GridPane playerList;
    @FXML
    private TextField playerNameField;
    @FXML
    public void addPlayer() {
        String playerName = playerNameField.getText();
        currentPlayers.add(playerName);

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
            currentPlayers.remove(playerName);
            playerList.getChildren().remove(rowContainer);
        });

        playerList.addRow(playerList.getRowCount(), rowContainer);
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
