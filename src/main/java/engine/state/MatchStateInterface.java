package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.CellNotAvailableException;
import exceptions.NoBoardFoundException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;

import java.util.List;

public interface MatchStateInterface {

    int getCurrentPlayer();

    void setNextPlayer();

    void drawTile() throws TileCacheEmptyException;

    void addBoard(BoardInterface board);

    void deleteBoard(int playerIndex) throws NoBoardFoundException;

    void fillBoardCell(int placementIndex) throws CellNotAvailableException;

    List<BoardInterface> getBoards();

    int getNumberOfBoards();

    TileInterface getExtractedTile();


    int getBoardIndex(String boardNickname) throws PlayerNameNotFoundException;
}
