package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import utils.TileLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MatchState {
    private HashMap<Integer, BoardInterface> boards;
    private ArrayList<TileInterface> tileCache;
    private TileInterface currentTile;

    public MatchState() throws FatalGameErrorException {
        try {
            initializeTileCollection();
        } catch (FileNotFoundException e) {
            throw new FatalGameErrorException();
        }
    }

    private void initializeTileCollection() throws FileNotFoundException {
        TileLoader tileLoader = new TileLoader();
        tileCache.addAll(tileLoader.loadTileList());
    }

    public Integer getCacheSize() {
        return tileCache.size();
    }

    public void drawTile() {
        int chosenTileIndex = ThreadLocalRandom.current().nextInt(getCacheSize() + 1);
        currentTile = tileCache.remove(chosenTileIndex);
    }

    public void addBoard(BoardInterface board) {
        boards.put(boards.size() + 1, board);
    }

    public void deleteBoard(Integer playerId) {
        boards.entrySet().removeIf(entry -> entry.getKey().equals(playerId));
    }

    public BoardInterface getBoard(Integer playerId) {
        return (BoardInterface) boards.get(playerId);
    }

    public Map<Integer, BoardInterface> getBoards() {
        return boards;
    }

    public int getNumberOfPlayers() {
        return boards.size();
    }

}
