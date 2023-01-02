package engine.controller;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;

import java.util.List;

public interface GameInterface {

    public TileInterface getCurrentTile();

    public int getCurrentPlayer();

    public void nextPlayer();

    public void nextTurn();

    public void addPlayer(String newPlayerName);

    public void removePlayer(int playerIndex);

    public List<Integer> getScores();

    public List<String> getNicknames();

    public List<BoardInterface> getPlayers();

    public BoardInterface getSinglePlayer(int playerIndex);

    public boolean isGameOver();

}
