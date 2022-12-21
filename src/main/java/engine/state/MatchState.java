package engine.state;

import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import utils.tile.TileLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MatchState {
    private final ArrayList<BoardInterface> boards =  new ArrayList<>();
    private final ArrayList<TileInterface> tileCache = new ArrayList<>();
    private TileInterface currentTile;
    private Integer currentPlayer;

    public MatchState() throws FatalGameErrorException {
        currentPlayer = 0;
        initializeTileCollection();
    }

    public int getCurrentPlayer() { return currentPlayer; }

    public void nextPlayer() { currentPlayer = currentPlayer + 1; }

    private void initializeTileCollection() {
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

    public BoardInterface getBoardOfPlayer(Integer playerId) {
        return boards.get(playerId);
    }

    public List<BoardInterface> getBoards() {
        return boards;
    }

    public int getNumberOfPlayers() {
        return boards.size();
    }

    public TileInterface getCurrentTile() { return currentTile; }

    public int getAllPlayerScore() {
        return 0;
    }

    public List<String> getPlayersNicknames() {
        List<String> nicknames = new ArrayList<>();
        for (BoardInterface playerBoard: boards) {
            nicknames.add(playerBoard.getNickname());
        }
        return nicknames;
    }
}
