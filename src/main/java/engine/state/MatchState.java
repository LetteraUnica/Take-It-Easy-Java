package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.CellNotAvailableException;
import exceptions.NoBoardFoundException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;
import utils.tile.TileLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MatchState implements MatchStateInterface {
    private final ArrayList<BoardInterface> boards =  new ArrayList<>();
    private final ArrayList<TileInterface> tileCache = new ArrayList<>();
    private TileInterface extractedTile;
    private Integer currentPlayer;

    public MatchState() {
        currentPlayer = 0;
        initializeTileCollection();
    }

    private void initializeTileCollection() {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        tileCache.addAll(tileList);
    }

    @Override
    public int getCurrentPlayer() { return currentPlayer; }

    @Override
    public void setNextPlayer() { currentPlayer = (currentPlayer + 1) % boards.size(); }

    @Override
    public void drawTile() throws TileCacheEmptyException {
        if (tileCache.isEmpty()) {
            throw new TileCacheEmptyException();
        }
        int chosenTileIndex = ThreadLocalRandom.current().nextInt(tileCache.size());
        extractedTile = tileCache.remove(chosenTileIndex);
    }

    @Override
    public void addBoard(BoardInterface board) {
        boards.add(board);
    }

    @Override
    public void deleteBoard(int playerIndex) throws NoBoardFoundException {
        if (boards.isEmpty()) {
            throw new NoBoardFoundException();
        }
        boards.remove(playerIndex);
    }

    @Override
    public void fillBoardCell(int placementIndex) throws CellNotAvailableException {
        boards.get(currentPlayer).placeTile(placementIndex, getExtractedTile());
    }

    @Override
    public List<BoardInterface> getBoards() { return boards.stream().map(BoardInterface::copy).toList(); }

    @Override
    public int getNumberOfBoards() {
        return boards.size();
    }

    @Override
    public TileInterface getExtractedTile() { return extractedTile; }

    @Override
    public int getBoardIndex(String boardNickname) throws PlayerNameNotFoundException {
        List<String> allNicknames = boards.stream().map(BoardInterface::getNickname).toList();
        if (!allNicknames.contains(boardNickname)) {
            throw new PlayerNameNotFoundException(boardNickname);
        }
        return boards.stream().map(BoardInterface::getNickname).toList().indexOf(boardNickname);
    }

}
