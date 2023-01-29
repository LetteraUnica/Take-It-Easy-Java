package engine.controller;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;

import java.util.List;

public interface GameInterface {

    TileInterface getCurrentTile();

    String getCurrentPlayer();

    void nextTurn();

    void addPlayer(String newPlayerName);

    void removePlayer(String playerName);

    List<Integer> getScores();

    List<String> getNicknames();

    List<BoardInterface> getPlayers();

    boolean isGameOver();

    BoardInterface getBoardOfPlayer(String playerName);

    TileInterface getTileOfPlayer(String playerName, int tileId);

    void placeTile(int candidateTilePlacement);

    boolean isCurrentPlayer(String playerName);

    List<String> getGameWinners();

    int getWinnersScore();
}