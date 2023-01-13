package engine.controller;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;

import java.util.List;

public interface GameInterface {

    public TileInterface getCurrentTile();

    public String getCurrentPlayer();

    public void nextPlayer();

    public void nextTurn();

    public void addPlayer(String newPlayerName);

    public void removePlayer(String playerName);

    public List<Integer> getScores();

    public List<String> getNicknames();

    public List<BoardInterface> getPlayers();

    public boolean isGameOver();

    public BoardInterface getBoardOfPlayer(String playerName);

    public TileInterface getTileOfPlayer(String playerName, int tileId);

    void placeTile(int candidateTilePlacement);
}