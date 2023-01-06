package ui.mainGame;

import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import exceptions.ReassignedControllerException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import ui.UIControllerInterface;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static utils.ui.UIUtils.*;

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

    @Override
    public void initController(GameInterface gameController) {
        this.gameController = gameController;

        initializePlayerList();
        initializeBoard();
        showCurrentTile();
        addBindingToPlaceTileButton();
    }

    private void showCurrentTile() {
        TileInterface currentTile = gameController.getCurrentTile();
        Group cell = drawCell(currentTile, 30);
        currentTilePane.getChildren().add(cell);
        cell.relocate(15, 30);

    }

    private Group drawCell(TileInterface tile, double radius) {
        Group cell = new Group();
        Polygon hexagon = new Polygon();
        hexagon.getStyleClass().add("tile");
        setPolygonSides(hexagon, radius, 6);
        boardPane.getChildren().add(hexagon);
        cell.getChildren().add(hexagon);

        Group cellText = new Group();
        double angle = -Math.PI / 2;
        double da = Math.PI / 3;
        Text text = new Text("top");
        text.setX(radius*Math.cos(angle));
        text.setY(radius*Math.sin(angle));
        cellText.getChildren().add(text);
        text = new Text("right");
        text.setX(radius*Math.cos(angle + 2*da));
        text.setY(radius*Math.sin(angle + 2*da));
        cellText.getChildren().add(text);
        text = new Text("left");
        text.setX(radius*Math.cos(angle + 4*da));
        text.setY(radius*Math.sin(angle + 4*da));
        cellText.getChildren().add(text);

        cell.getChildren().add(cellText);
        cellText.relocate(-35, -35);
        return cell;
    }

    private void addBindingToPlaceTileButton() {
        placeTileButton.disableProperty().bind(Bindings.equal(-1, new IntegerBinding() {
            @Override
            protected int computeValue() {
                return candidateTilePlacement;
            }
        }));
    }

    private void initializePlayerList() {
        for (String playerName : gameController.getNicknames()) {
            addPlayer(playerName);
        }
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
            try {
                drawBoard(playerName);
            } catch (FatalGameErrorException e) {
                e.printStackTrace();
            }
        });

        viewPlayerButton.disableProperty().bind(Bindings.equal(playerName, new StringBinding() {
            @Override
            protected String computeValue() {
                return gameController.getCurrentPlayerName();
            }
        }));
    }

    private void initializeBoard() {
        try {
            drawBoard(gameController.getCurrentPlayerName());
        } catch (FatalGameErrorException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void drawBoard(String playerName) throws FatalGameErrorException {
        BoardInterface board = gameController.getBoardOfPlayer(playerName);
        List<Point2D> hexagonCenterCoordinates = board.getEuclideanCoordinates();
        if (hexagonCenterCoordinates.size() < 1) {
            throw new FatalGameErrorException();
        }

        double radius = 36;
        int n_hexagons = 5;
        double angle = Math.PI / 6;
        insertPadding(radius, n_hexagons, angle);

        double maxX = hexagonCenterCoordinates.stream().max(Comparator.comparing(Point2D::getX)).get().getX();
        double minX = hexagonCenterCoordinates.stream().min(Comparator.comparing(Point2D::getX)).get().getX();
        double maxY = hexagonCenterCoordinates.stream().max(Comparator.comparing(Point2D::getY)).get().getY();
        double minY = hexagonCenterCoordinates.stream().min(Comparator.comparing(Point2D::getY)).get().getY();

        for (Point2D centerPoint : hexagonCenterCoordinates) {
            double x = rescaleCoordinate(centerPoint.getX(), minX, maxX, boardPane.getPrefWidth() - 2 * boardPane.getInsets().getRight());
            double y = rescaleCoordinate(centerPoint.getY(), minY, maxY, boardPane.getPrefHeight() - 2 * boardPane.getInsets().getBottom());
            Polygon tile = new Polygon();
            tile.getStyleClass().add("tile");
            setPolygonSides(tile, radius, 6);
            boardPane.getChildren().add(tile);
            tile.relocate(x, y);
        }
    }

    private void insertPadding(double radius, int n_hexagons, double angle) {
        double height_width_ratio = 2 * (n_hexagons - 1) * Math.cos(angle) / (n_hexagons * (1 + Math.cos(2 * angle)) - 2);
        boardPane.setPadding(
                new Insets(
                        radius * Math.cos(angle),
                        radius * height_width_ratio,
                        radius * Math.cos(angle),
                        radius * height_width_ratio
                )
        );
    }

    @FXML
    public void returnToLobby(ActionEvent e) throws IOException, ReassignedControllerException {
        Navigator navigator = new Navigator();
        navigator.navigateWithController(getStage(e), NavigationConstants.LOBBY_FXML, gameController);
    }
}
