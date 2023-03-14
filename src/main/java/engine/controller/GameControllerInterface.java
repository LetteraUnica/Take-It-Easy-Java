package engine.controller;

import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;
import javafx.geometry.Point2D;

import java.util.List;

public interface GameControllerInterface {

    TileInterface getCurrentTile();

    String getCurrentPlayerName();

    void nextTurn() throws TileCacheEmptyException;

    void addPlayer(String newPlayerName);

    void removePlayer(String playerName) throws NoBoardFoundException, PlayerNameNotFoundException;

    List<String> getPlayersNicknames();

    boolean isGameOver();

    TileInterface getTileOf(String playerName, int tileId) throws PlayerNameNotFoundException;

    void placeTileIn(int candidateTilePlacement);

    List<Integer> getScores();

    List<String> getGameWinners();

    int getWinnersScore();

    List<Point2D> getRepresentationOf(String playerName) throws PlayerNameNotFoundException, NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException;

    boolean isLastPlayer();
}