package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.TileCacheEmptyException;

import java.util.List;

public interface MatchStateInterface {

    int getCurrentPlayer();

    void setNextPlayer();

    void drawTile() throws TileCacheEmptyException;

    void addBoard(BoardInterface board);

    void deleteBoard(int playerIndex) throws NoBoardFoundException;

    void fillBoardCell(int placementIndex);

    List<BoardInterface> getBoards();

    int getNumberOfBoards();

    TileInterface getCurrentTile();


    int getBoardIndex(String boardNickname);
}
