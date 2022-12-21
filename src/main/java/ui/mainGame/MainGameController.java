package ui.mainGame;

import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import exceptions.ReassignedControllerException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import ui.UIControllerInterface;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;
import java.util.Objects;

import static utils.ui.UIUtils.*;

public class MainGameController implements UIControllerInterface {
    @FXML
    private Pane boardPane;
    @FXML
    private GridPane playerListPane;
    @FXML
    private Button returnToLobbyButton;
    @FXML
    private Button placeTileButton;

    private String currentlyViewedBoard;

    private GameInterface gameController;

    @Override
    public void initController(GameInterface gameController) {
        this.gameController = gameController;

        initializePlayerList();
        initializeBoard();
    }

    private void initializePlayerList() {
        for (String playerName : gameController.getNicknames()){
            addPlayer(playerName);
        }
        updateButtons();
    }

    private void addPlayer(String playerName) {
        Pane rowContainer = createRowContainer();
        addPlayerName(playerName, rowContainer, 100, 16);
        addViewButton(playerName, rowContainer);
        playerListPane.addRow(playerListPane.getRowCount(), rowContainer);
    }

    private void addViewButton(String playerName, Pane rowContainer) {
        Button viewPlayerButton = new Button("View");
        rowContainer.getChildren().add(viewPlayerButton);
        viewPlayerButton.setStyle("-fx-font-size:14");
        viewPlayerButton.relocate(120, 0);
        viewPlayerButton.setOnAction(event -> {
            viewBoard(playerName);
        });
        BooleanBinding invalid = Bindings.createBooleanBinding(() -> Objects.equals(gameController.getCurrentPlayerName(), playerName));
        viewPlayerButton.setDisable(invalid.get());
    }

    private static void setPolygonSides(Polygon polygon, double centerX, double centerY, double radius, int sides) {
        polygon.getPoints().clear();
        final double angleStep = Math.PI * 2 / sides;
        double angle = angleStep / 2;
        for (int i = 0; i < sides; i++, angle += angleStep) {
            polygon.getPoints().addAll(
                    Math.sin(angle) * radius + centerX, // x coordinate of the corner
                    Math.cos(angle) * radius + centerY // y coordinate of the corner
            );
        }
    }
    @FXML
    private void viewBoard(String playerName) {
        BoardInterface board = gameController.getBoardOfPlayer(playerName);
        float[][] hexagonCenterCoordinates = board.getEuclideanCoordinates();
        for (float[] coordinate: hexagonCenterCoordinates) {
            float x = coordinate[0];
            float y = coordinate[1];
            Polygon tile = new Polygon();
            tile.getStyleClass().add("tile");
            setPolygonSides(tile, (x+1.2)*110, (y+1.3)*110, 35, 6);
            boardPane.getChildren().add(tile);
        }

        currentlyViewedBoard = playerName;
        updateButtons();
    }

    private void updateButtons() {
        disableButtonOfCurrentPlayer();
    }

    private void disableButtonOfCurrentPlayer() {

    }

    private void initializeBoard() {
        viewBoard(gameController.getCurrentPlayerName());
    }

    @FXML
    public void returnToLobby(ActionEvent e) throws IOException, ReassignedControllerException {
        Navigator navigator = new Navigator();
        navigator.navigateWithController(getStage(e), NavigationConstants.LOBBY_FXML, gameController);
    }
}
