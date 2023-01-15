package engine;

import engine.controller.GameController;
import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.tile.TileLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

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
            controller.nextTurn();
            TileInterface currentTile = controller.getCurrentTile();
            controller.getBoardOfPlayer("pincopallo").placeTile(i, currentTile);
            i++;
        }
        assertTrue(controller.isGameOver());
    }

}
