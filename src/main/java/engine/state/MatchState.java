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

    private void initializeTileCollection() {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        tileCache.addAll(tileList);
    }

    public int getCurrentPlayer() { return currentPlayer; }

    public void nextPlayer() { currentPlayer = (currentPlayer + 1) % boards.size(); }

    public Integer getCacheSize() {
        return tileCache.size();
    }

    public void drawTile() {
        int chosenTileIndex = ThreadLocalRandom.current().nextInt(getCacheSize());
        currentTile = tileCache.remove(chosenTileIndex);
    }

    public void addBoard(BoardInterface board) {
        boards.add(board);
    }

    public void deleteBoard(int playerIndex) {
        boards.remove(playerIndex);
    }

    public BoardInterface getBoardOfPlayer(Integer playerId) {
        return boards.get(playerId);
    }

    public List<BoardInterface> getBoards() {
        return boards;
    }

    public int getNumberOfBoards() {
        return boards.size();
    }

    public TileInterface getCurrentTile() { return currentTile; }

    public List<Integer> getAllBoardsScore() {
        List<Integer> scores = new ArrayList<>();
        for (BoardInterface playerBoard: boards) {
            scores.add(playerBoard.getScore());
        }
        return scores;
    }

    public List<String> getBoardsNicknames() {
        List<String> nicknames = new ArrayList<>();
        for (BoardInterface playerBoard: boards) {
            nicknames.add(playerBoard.getNickname());
        }
        return nicknames;
    }
}
