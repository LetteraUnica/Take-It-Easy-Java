package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.TileCacheEmptyException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import utils.tile.TileLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MatchState implements MatchStateInterface {
    private final ArrayList<BoardInterface> boards =  new ArrayList<>();
    private final ArrayList<TileInterface> tileCache = new ArrayList<>();
    private TileInterface currentTile;
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
    public void nextPlayer() { currentPlayer = (currentPlayer + 1) % boards.size(); }

    @Contract(pure = true)
    private @NotNull Integer getCacheSize() {
        return tileCache.size();
    }

    @Override
    public void drawTile() throws TileCacheEmptyException {
        if (tileCache.isEmpty()) {
            throw new TileCacheEmptyException();
        }
        int chosenTileIndex = ThreadLocalRandom.current().nextInt(getCacheSize());
        currentTile = tileCache.remove(chosenTileIndex);
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
    public List<BoardInterface> getBoards() {
        return boards;
    }

    @Override
    public int getNumberOfBoards() {
        return boards.size();
    }

    @Override
    public TileInterface getCurrentTile() { return currentTile; }

}
