package engine;

import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import engine.state.MatchState;
import exceptions.FatalGameErrorException;
import org.junit.jupiter.api.Test;
import utils.tile.TileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchStateTest {

    MatchState state = new MatchState();

    public MatchStateTest() throws FatalGameErrorException {}

    @Test
    void testStartingMatchStateAvailableTiles() {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        assertEquals(tileList.size(), state.getCacheSize());
    }

    @Test
    void testStartingCurrentPlayer() {
        assertEquals(0, state.getCurrentPlayer());
    }

    @Test
    void testNextPlayer() {
        int currentPlayer = state.getCurrentPlayer();
        state.nextPlayer();
        int nextPlayer = state.getCurrentPlayer();
        assertEquals(nextPlayer, currentPlayer + 1);
    }

    @Test
    void testDrawTileReducesCacheSize() {
        int currentCacheSize = state.getCacheSize();
        state.drawTile();
        assertEquals(currentCacheSize - 1, state.getCacheSize());
    }

    @Test
    void testAddBoard() {
        int numberOfBoards = state.getNumberOfBoards();
        BoardInterface newBoard = new BoardClassic("macro");
        state.addBoard(newBoard);
        assertEquals(numberOfBoards + 1, state.getNumberOfBoards());
    }

    @Test
    void testDeleteBoard() {
        BoardInterface newBoard1 = new BoardClassic("macro");
        state.addBoard(newBoard1);
        BoardInterface newBoard2 = new BoardClassic("chic");
        state.addBoard(newBoard2);
        int numberOfBoards = state.getNumberOfBoards();
        state.deleteBoard(0);
        assertEquals(numberOfBoards - 1, state.getNumberOfBoards());
    }

    @Test
    void testGetNumberOfBoards() {
        BoardInterface newBoard1 = new BoardClassic("macro");
        state.addBoard(newBoard1);
        BoardInterface newBoard2 = new BoardClassic("chic");
        state.addBoard(newBoard2);
        BoardInterface newBoard3 = new BoardClassic("pinco");
        state.addBoard(newBoard3);
        BoardInterface newBoard4 = new BoardClassic("pallo");
        state.addBoard(newBoard4);
        assertEquals(4, state.getNumberOfBoards());
    }
    
    @Test
    void testGetCurrentTile() {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        state.drawTile();
        boolean isInTileList = false;
        for (TileInterface tile: tileList) {
            if (state.getCurrentTile().equals(tile)) {
                isInTileList = true;
            }
        }
        assertTrue(isInTileList);
    }

    @Test
    void testGetBoardsNicknames() {
        BoardInterface newBoard1 = new BoardClassic("macro");
        state.addBoard(newBoard1);
        BoardInterface newBoard2 = new BoardClassic("chic");
        state.addBoard(newBoard2);
        BoardInterface newBoard3 = new BoardClassic("pinco");
        state.addBoard(newBoard3);
        BoardInterface newBoard4 = new BoardClassic("pallo");
        state.addBoard(newBoard4);
        List<String> trueNames = new ArrayList<>(Arrays.asList("macro", "chic", "pinco", "pallo"));
        List<String> names = state.getBoardsNicknames();
        assertEquals(trueNames, names);
    }

    @Test
    void testGetBoardOfPlayer() {
        BoardInterface newBoard1 = new BoardClassic("macro");
        state.addBoard(newBoard1);
        BoardInterface newBoard2 = new BoardClassic("chic");
        state.addBoard(newBoard2);
        BoardInterface boardOfMacro = state.getBoardOfPlayer(0);
        assertEquals(boardOfMacro.getNickname(), newBoard1.getNickname());
        BoardInterface boardOfChic = state.getBoardOfPlayer(1);
        assertEquals(boardOfChic.getNickname(), newBoard2.getNickname());
    }

    @Test
    void testGetBoards() {
        List<String> nicknames = Arrays.asList("macro", "chic");
        for (String player: nicknames) {
            state.addBoard(new BoardClassic(player));
        }
        List<BoardInterface> boards = state.getBoards();
        for (int i=0; i < state.getNumberOfBoards(); i++) {
            assertEquals(state.getBoards().get(i).getNickname(), boards.get(i).getNickname());
        }
    }

}
