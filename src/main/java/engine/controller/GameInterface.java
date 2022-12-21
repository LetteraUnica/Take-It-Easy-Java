package engine.controller;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;

import java.util.List;

public interface GameInterface {

    public TileInterface getCurrentTile();

    public int getCurrentPlayer();

    public void nextTurn();

    public void addPlayer(String newPlayerName);

    public void removePlayer(int playerIndex);

    public int getCurrentPlayerScore();

    public boolean isGameOver();

/*    public List<String> getPlayerNames();
    public List<BoardInterface> getBoards();
    public BoardInterface getBoardOfPlayer(int playerIndex);
    public List<Integer> getAllPlayersScore();*/

}
