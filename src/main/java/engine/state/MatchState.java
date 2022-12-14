package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import utils.TileLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MatchState {
    private ArrayList<BoardInterface> boards =  new ArrayList<>();
    private ArrayList<TileInterface> tileCache = new ArrayList<>();
    private TileInterface currentTile;
    private Integer currentPlayer;

    public MatchState() throws FatalGameErrorException {
        currentPlayer = 0;
        try {
            initializeTileCollection();
        } catch (FileNotFoundException e) {
            throw new FatalGameErrorException();
        }
        System.out.println(getCacheSize());
    }

    public int getCurrentPlayer() { return currentPlayer; }

    public void nextPlayer() { currentPlayer = currentPlayer + 1; }

    private void initializeTileCollection() throws FileNotFoundException {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        tileCache.addAll(tileList);
    }

    public Integer getCacheSize() {
        return tileCache.size();
    }

    public void drawTile() {
        int chosenTileIndex = ThreadLocalRandom.current().nextInt(getCacheSize() + 1);
        currentTile = tileCache.remove(chosenTileIndex);
    }

    public void addBoard(BoardInterface board) {
        boards.add(board);
    }

    public void deleteBoard(Integer playerIndex) {
        boards.remove(playerIndex);
    }

    public BoardInterface getBoard(Integer playerId) {
        return (BoardInterface) boards.get(playerId);
    }

    public ArrayList<BoardInterface> getBoards() {
        return boards;
    }

    public int getNumberOfPlayers() {
        return boards.size();
    }

    public TileInterface getCurrentTile() { return currentTile; }

    public int getCurrentPlayerScore() {
        return 0;
    }
}
