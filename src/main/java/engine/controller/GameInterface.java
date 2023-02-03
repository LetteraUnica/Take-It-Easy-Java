package engine.controller;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;

import java.util.List;

public interface GameInterface {

    TileInterface getCurrentTile();

    String getCurrentPlayer();

    void nextTurn() throws TileCacheEmptyException;

    boolean isLastPlayer();

    void addPlayer(String newPlayerName);

    void removePlayer(String playerName) throws NoBoardFoundException, PlayerNameNotFoundException;

    int getPlayerIndex(String playerName) throws PlayerNameNotFoundException;

    List<Integer> getScores();

    List<String> getNicknames();

    List<BoardInterface> getPlayers();

    boolean isGameOver();

    BoardInterface getBoardOfPlayer(String playerName) throws PlayerNameNotFoundException;

    TileInterface getTileOfPlayer(String playerName, int tileId) throws PlayerNameNotFoundException;

    void placeTile(int candidateTilePlacement);

    boolean isCurrentPlayer(String playerName) throws PlayerNameNotFoundException;

    List<String> getGameWinners();

    int getWinnersScore();
}