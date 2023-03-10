package engine;

import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import engine.state.MatchState;
import engine.state.MatchStateInterface;
import exceptions.NoBoardFoundException;
import exceptions.TileCacheEmptyException;
import org.junit.jupiter.api.Test;
import utils.tile.TileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchStateTest {

    MatchStateInterface state = new MatchState();

    @Test
    void testStartingCurrentPlayer() {
        assertEquals(0, state.getCurrentPlayer());
    }

    @Test
    void testAddBoard() {
        int numberOfBoards = state.getNumberOfBoards();
        BoardInterface newBoard = new BoardClassic("macro");
        state.addBoard(newBoard);
        assertEquals(numberOfBoards + 1, state.getNumberOfBoards());
    }

    @Test
    void testNextPlayer() {
        state.addBoard(new BoardClassic("caio"));
        state.addBoard(new BoardClassic("tizio"));
        int currentPlayer = state.getCurrentPlayer();
        state.nextPlayer();
        int nextPlayer = state.getCurrentPlayer();
        assertEquals(nextPlayer, currentPlayer + 1);
    }

    @Test
    void testDeleteBoard() throws NoBoardFoundException {
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
    void testGetCurrentTile() throws TileCacheEmptyException {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        state.drawTile();
        boolean isInTileList = false;
        for (TileInterface tile: tileList) {
            if (state.getCurrentTile().getIdTile() == tile.getIdTile() && state.getCurrentTile().getValues().equals(tile.getValues())) {
                isInTileList = true;
            }
        }
        assertTrue(isInTileList);
    }

    @Test
    void testGetBoards() {
        List<String> nicknames = Arrays.asList("macro", "chic");
        for (String player: nicknames) {
            state.addBoard(new BoardClassic(player));
        }
        ArrayList<BoardInterface> boards = new ArrayList<>(state.getBoards());
        BoardInterface newBoard = new BoardClassic("pinco");
        boards.add(newBoard);
        List<String> modifiedGameNicknames = boards.stream().map(BoardInterface::getNickname).toList();
        assertNotEquals(nicknames, modifiedGameNicknames);
    }

}
