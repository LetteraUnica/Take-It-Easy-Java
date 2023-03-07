package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.TileCacheEmptyException;

import java.util.List;

public interface MatchStateInterface {

    int getCurrentPlayer();

    void nextPlayer();

    void drawTile() throws TileCacheEmptyException;

    void addBoard(BoardInterface board);

    void deleteBoard(int playerIndex) throws NoBoardFoundException;

    List<BoardInterface> getBoards();

    int getNumberOfBoards();

    TileInterface getCurrentTile();
}
