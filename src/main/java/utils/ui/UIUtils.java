package utils.ui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
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

    public static void setPolygonSides(Polygon polygon, double radius, int sides) {
        polygon.getPoints().clear();
        final double angleStep = Math.PI * 2 / sides;
        double angle = angleStep / 2;
        for (int i = 0; i < sides; i++, angle += angleStep) {
            polygon.getPoints().addAll(
                    Math.sin(angle) * radius, // x coordinate of the corner
                    Math.cos(angle) * radius // y coordinate of the corner
            );
        }
    }

    public static double rescaleCoordinate(double coordinate, double minValue, double maxValue, double desiredRange) {
        return (coordinate - minValue) / (maxValue - minValue) * desiredRange;
    }
}
