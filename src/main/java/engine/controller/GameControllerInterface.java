package engine.controller;

import engine.model.board.BoardInterface;
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

    boolean isLastPlayer();

    void addPlayer(String newPlayerName);

    void removePlayer(String playerName) throws NoBoardFoundException, PlayerNameNotFoundException;

    List<String> getPlayersNicknames();

    boolean isGameOver();

    BoardInterface getBoardOf(String playerName) throws PlayerNameNotFoundException;

    TileInterface getTileOf(String playerName, int tileId) throws PlayerNameNotFoundException;

    void placeTileIn(int candidateTilePlacement);

    List<Integer> getScores();

    List<String> getGameWinners();

    int getWinnersScore();

    List<Point2D> getBoardCoordinatesOf(String playerName) throws PlayerNameNotFoundException, NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException;
}