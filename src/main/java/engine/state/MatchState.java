package engine.state;

import engine.model.board.Board;
import engine.model.tile.Tile;
import exceptions.FatalGameErrorException;
import utils.TileLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchState {
    private HashMap<Integer, Board> boards;
    private List<Tile> tileCollection;

    public MatchState() throws FatalGameErrorException {
        try {
            initializeTileCollection();
        } catch (FileNotFoundException e) {
            throw new FatalGameErrorException();
        }

    }

    private void initializeTileCollection() throws FileNotFoundException {
        TileLoader tileLoader = new TileLoader();
        this.tileCollection = tileLoader.loadTileList();
    }

    public void addBoard(Board board) {

    }

    public void deleteBoard(String name) {

    }

    public void renameBoard(String oldName, String newName) {

    }

    public Board getBoard(String name) {
        return (Board) boards.get(name);
    }

    public Map getBoards() {
        return boards;
    }

    public int getNumberOfPlayers() {
        return boards.size();
    }
}
