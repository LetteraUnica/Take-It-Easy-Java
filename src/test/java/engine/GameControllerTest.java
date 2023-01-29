package engine;

import engine.controller.GameController;
import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.FatalGameErrorException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameInterface controller = new GameController();

    GameControllerTest() throws FatalGameErrorException {}

    private void fillBoards(GameInterface controller) {
        for (String playerName: controller.getNicknames()) {
            BoardInterface testBoard = controller.getBoardOfPlayer(playerName);
            for (int i = 0; i < 19; ++i) {
                TileInterface testStandardTile = new Tile(i, 1, 1, 1);
                testBoard.placeTile(i, testStandardTile);
            }
        }
    }

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

    @Test
    void testIsCurrentPlayer() {
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        assertTrue(controller.isCurrentPlayer("pinco"));
        controller.nextTurn();
        assertTrue(controller.isCurrentPlayer("pallo"));
    }

    @Test
    void testGetGameWinners() {
        controller.addPlayer("caio");
        controller.addPlayer("sempronio");
        fillBoards(controller);
        ArrayList<String> winners = new ArrayList<>(List.of("caio"));
        assertNotEquals(controller.getGameWinners(), winners);
        winners.add("sempronio");
        assertEquals(controller.getGameWinners(), winners);
    }

    @Test
    void testGetWinnersScore() {
        controller.addPlayer("caio");
        fillBoards(controller);
        assertEquals(57, controller.getWinnersScore());
    }

}
