package engine;

import engine.controller.GameController;
import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameInterface controller = new GameController();

    public GameControllerTest() throws FatalGameErrorException {}

    @Test
    void testRemovePlayer() {
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        controller.removePlayer("pallo");
        List<String> players = controller.getNicknames();
        assertFalse(players.contains("pallo"));
    }

    @Test
    void testGetBoardOfPlayer() {
        controller.addPlayer("pinco");
        BoardInterface boardOfPallo = controller.getBoardOfPlayer("pinco");
        assertEquals("pinco", boardOfPallo.getNickname());
    }

    @Test
    void testIsGameOver() {
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

}
