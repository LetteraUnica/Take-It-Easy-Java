package utils.ui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UIUtils {
    public static Stage getStage(ActionEvent e) {
        return (Stage) ((Node) e.getSource()).getScene().getWindow();
    }

    public static void addPlayerName(String playerName, Pane rowContainer, int charactersTillEllipsize, int fontSize) {
        Label playerNameText = new Label(playerName);
        rowContainer.getChildren().add(playerNameText);
        playerNameText.setFont(Font.font(fontSize));
        playerNameText.setMaxWidth(charactersTillEllipsize);
        playerNameText.setTextOverrun(OverrunStyle.ELLIPSIS);
        playerNameText.relocate(0, 0);
    }

    public static Pane createRowContainer() {
        Pane rowContainer = new Pane();
        rowContainer.setPadding(new javafx.geometry.Insets(20, 0, 20, 0));
        rowContainer.setPrefHeight(30);
        return rowContainer;
    }
}
