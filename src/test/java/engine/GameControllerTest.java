package engine;

import engine.controller.GameController;
import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;
import org.junit.jupiter.api.Test;
import utils.BoardFiller;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameInterface controller = new GameController();
    BoardFiller filler = new BoardFiller();

    GameControllerTest() throws TileCacheEmptyException {}

    @Test
    void testRemovePlayer() throws NoBoardFoundException, PlayerNameNotFoundException {
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        controller.removePlayer("pallo");
        List<String> players = controller.getNicknames();
        assertFalse(players.contains("pallo"));
    }

    @Test
    void testGetBoardOfPlayer() throws PlayerNameNotFoundException {
        controller.addPlayer("pinco");
        BoardInterface boardOfPallo = controller.getBoardOfPlayer("pinco");
        assertEquals("pinco", boardOfPallo.getNickname());
    }

    @Test
    void testIsGameOver() throws TileCacheEmptyException, PlayerNameNotFoundException {
        controller.addPlayer("pincopallo");
        int i = 0;
        while (!(controller.getBoardOfPlayer("pincopallo").isBoardFull())) {
            TileInterface currentTile = controller.getCurrentTile();
            controller.getBoardOfPlayer("pincopallo").placeTile(i, currentTile);
            i++;
            if (!(controller.getBoardOfPlayer("pincopallo").isBoardFull())) {
                controller.nextTurn();
            }
        }
        assertTrue(controller.isGameOver());
    }

    @Test
    void testIsCurrentPlayer() throws TileCacheEmptyException, PlayerNameNotFoundException {
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        assertTrue(controller.isCurrentPlayer("pinco"));
        controller.nextTurn();
        assertTrue(controller.isCurrentPlayer("pallo"));
    }

    @Test
    void testGetGameWinners() throws PlayerNameNotFoundException {
        controller.addPlayer("caio");
        controller.addPlayer("sempronio");
        filler.fillAllBoardsHavingController(controller, new Tile(1, 1, 1,1), 0);
        ArrayList<String> winners = new ArrayList<>(List.of("caio"));
        assertNotEquals(controller.getGameWinners(), winners);
        winners.add("sempronio");
        assertEquals(controller.getGameWinners(), winners);
    }

    @Test
    void testGetWinnersScore() throws PlayerNameNotFoundException {
        controller.addPlayer("caio");
        filler.fillAllBoardsHavingController(controller, new Tile(1, 1, 1,1), 0);
        assertEquals(57, controller.getWinnersScore());
    }

}
