package ui.windows.mainGame;

import engine.controller.GameInterface;
import engine.model.tile.TileInterface;
import exceptions.NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException;
import exceptions.PlayerNameNotFoundException;
import exceptions.ReassignedControllerException;
import exceptions.TileCacheEmptyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;
import ui.windows.UIControllerInterface;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static ui.utils.UIUtils.*;

public class MainGameController implements UIControllerInterface {
    @FXML
    private Pane boardPane;
    @FXML
    private GridPane playerListPane;
    @FXML
    private Button placeTileButton;
    @FXML
    private Pane currentTilePane;

    private GameInterface gameController;

    private int candidateTilePlacement = -1;
    private String viewedPlayer;

    @Override
    public void initController(GameInterface gameController) {
        this.gameController = gameController;
        viewedPlayer = this.gameController.getCurrentPlayer();
        updateView();
    }

    private void updateView() {
        try {
            drawBoard(viewedPlayer);
        } catch (PlayerNameNotFoundException | NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException e) {
            e.printStackTrace();
        }
        updatePlaceTileButton();
        initializePlayerList();
        showCurrentTile();
    }

    private void showCurrentTile() {
        currentTilePane.getChildren().clear();
        TileInterface currentTile = gameController.getCurrentTile();
        Polygon cell = drawHexagonalTile(Constants.tileRadius);
        cell.getStyleClass().add("tile");
        currentTilePane.getChildren().add(cell);

        cell.relocate(12, 28);
        addNumbersToTile(currentTile, currentTilePane, 27, 32);
    }

    private void updatePlaceTileButton() {
        placeTileButton.setDisable(candidateTilePlacement == -1 || !viewedPlayer.equals(gameController.getCurrentPlayer()));
    }

    private void initializePlayerList() {
        playerListPane.getChildren().clear();
        for (String playerName : gameController.getNicknames()) {
            addToPlayerList(playerName);
        }
    }

    private void addToPlayerList(String playerName) {
        Pane rowContainer = createRowContainer();
        addPlayerName(playerName, rowContainer, 90, 16);
        String buttonText = playerName.equals(gameController.getCurrentPlayer()) ? "Return" : "View";
        addButtonToPlayerList(playerName, rowContainer, buttonText);
        playerListPane.addRow(playerListPane.getRowCount(), rowContainer);
    }

    private void addButtonToPlayerList(String playerName, Pane rowContainer, String buttonText) {
        Button viewPlayerButton = new Button(buttonText);
        rowContainer.getChildren().add(viewPlayerButton);
        viewPlayerButton.setStyle("-fx-font-size:14");
        viewPlayerButton.relocate(100, 0);
        viewPlayerButton.setOnAction(event -> {
            viewedPlayer = playerName;
            updateView();
        });
        viewPlayerButton.setDisable(viewedPlayer.equals(playerName));
    }

    @FXML
    private void drawBoard(String playerName) throws PlayerNameNotFoundException, NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException {
        boardPane.getChildren().clear();

        insertBoardPanePadding();

        List<Point2D> hexagonCenterCoordinates = gameController.getCoordinatesOfBoard(playerName);
        double maxX = Objects.requireNonNull(hexagonCenterCoordinates
                .stream()
                .max(Comparator.comparing(Point2D::getX))
                .orElse(null)).getX();
        double minX = hexagonCenterCoordinates.stream().min(Comparator.comparing(Point2D::getX)).get().getX();
        double maxY = hexagonCenterCoordinates.stream().max(Comparator.comparing(Point2D::getY)).get().getY();
        double minY = hexagonCenterCoordinates.stream().min(Comparator.comparing(Point2D::getY)).get().getY();
        for (int tileId = 0; tileId < hexagonCenterCoordinates.size(); tileId++) {
            Point2D centerPoint = hexagonCenterCoordinates.get(tileId);
            Polygon cell = drawHexagonalTile(Constants.tileRadius);
            boardPane.getChildren().add(cell);
            double x = rescaleCoordinate(centerPoint.getX(), minX, maxX,
                    boardPane.getPrefWidth() - 2 * boardPane.getInsets().getRight());
            double y = rescaleCoordinate(centerPoint.getY(), minY, maxY,
                    boardPane.getPrefHeight() - 2 * boardPane.getInsets().getBottom());
            cell.relocate(x, y);
            if (isCurrentPlayer(playerName)) {
                cell.getStyleClass().add("playerCell");
            } else {
                cell.getStyleClass().add("opponentCell");
            }
            TileInterface tile = gameController.getTileOfPlayer(playerName, tileId);
            if (tileId == candidateTilePlacement && isCurrentPlayer(playerName)) {
                tile = gameController.getCurrentTile();
            }
            if (tile != null) {
                cell.getStyleClass().add("tile");
                addNumbersToTile(tile, boardPane, x + 15, y + 5);
            } else if (isCurrentPlayer(viewedPlayer)) {
                addTileInteraction(tileId, cell);
            }
        }
    }

    private boolean isCurrentPlayer(String playerName) {
        return gameController.getCurrentPlayer().equals(playerName);
    }

    private void addTileInteraction(int tileId, Polygon cell) {
        cell.setOnMouseClicked(event -> {
            candidateTilePlacement = tileId;
            updateView();
        });
    }

    private void addNumbersToTile(TileInterface tile, Pane boardPane, double x, double y) {
        Group tileNumbers = setNumberLocation(tile);
        boardPane.getChildren().add(tileNumbers);
        tileNumbers.relocate(x, y);
    }

    private Group setNumberLocation(TileInterface tile) {
        double startAngle = -Math.PI / 2;
        double increment = Math.PI * 2 / 3;
        List<Integer> tileValues = List.of(tile.getTopPath(), tile.getRightPath(), tile.getLeftPath());
        List<String> tileColors = List.of("#ffffff", "#FFA500", "#00FF00");
        Group tileNumbers = new Group();
        for (int i = 0; i < 3; i++) {
            Text text = new Text(String.valueOf(tileValues.get(i)));
            text.setX(Constants.numberRadius * Math.cos(startAngle + i * increment));
            text.setY(Constants.numberRadius * Math.sin(startAngle + i * increment));
            text.getStyleClass().add("tileText");
            text.setFill(Paint.valueOf(tileColors.get(i)));
            tileNumbers.getChildren().add(text);
        }
        return tileNumbers;
    }

    private void insertBoardPanePadding() {
        int n_hexagons = 5;
        double angle = Math.PI / 6;
        double height_width_ratio = 2 * (n_hexagons - 1) * Math.cos(angle) / (n_hexagons * (1 + Math.cos(2 * angle)) - 2);
        boardPane.setPadding(
                new Insets(
                        Constants.tileRadius * Math.cos(angle),
                        Constants.tileRadius * height_width_ratio,
                        Constants.tileRadius * Math.cos(angle),
                        Constants.tileRadius * height_width_ratio
                )
        );
    }

    @FXML
    public void returnToLobby(ActionEvent e) throws IOException {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.LOBBY_FXML);
    }

    @FXML
    public void placeTile(ActionEvent e) throws ReassignedControllerException, IOException, TileCacheEmptyException {
        gameController.placeTile(candidateTilePlacement);
        candidateTilePlacement = -1;
        if (gameController.isGameOver()) {
            Navigator navigator = new Navigator();
            navigator.navigateWithController(getStage(e), NavigationConstants.GAME_OVER_FXML, gameController);
        } else {
            gameController.nextTurn();
            viewedPlayer = gameController.getCurrentPlayer();
            updateView();
        }
    }
}
