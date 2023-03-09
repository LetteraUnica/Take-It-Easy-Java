package engine;

import engine.controller.GameController;
import engine.controller.GameControllerInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.NoBoardFoundException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameControllerInterface controller = new GameController();

    private void trivialPlayer() throws PlayerNameNotFoundException, TileCacheEmptyException {
        for (String playerName: controller.getPlayersNicknames()) {
            int i = 0;
            while (!(controller.getBoardOf(playerName).isBoardFull())) {
                controller.placeTileIn(i);
                i++;
            }
            controller.nextTurn();
        }
    }

    GameControllerTest() throws TileCacheEmptyException {}

    private boolean isCurrentPlayer(String playerName) throws PlayerNameNotFoundException {
        if (!controller.getPlayersNicknames().contains(playerName)) {
            throw new PlayerNameNotFoundException(playerName);
        }
        return controller.getCurrentPlayerName().equals(playerName);
    }

    @Test
    void testRemovePlayer() throws NoBoardFoundException, PlayerNameNotFoundException {
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        controller.removePlayer("pallo");
        List<String> players = controller.getPlayersNicknames();
        assertFalse(players.contains("pallo"));
    }

    @Test
    void testGetBoardOfPlayer() throws PlayerNameNotFoundException {
        controller.addPlayer("pinco");
        BoardInterface boardOfPallo = controller.getBoardOf("pinco");
        assertEquals("pinco", boardOfPallo.getNickname());
    }

    @Test
    void testIsGameOver() throws TileCacheEmptyException, PlayerNameNotFoundException {
        controller.addPlayer("pincopallo");
        trivialPlayer();
        assertTrue(controller.isGameOver());
    }

    @Test
    void testIsCurrentPlayer() throws TileCacheEmptyException, PlayerNameNotFoundException {
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        assertTrue(isCurrentPlayer("pinco"));
        controller.nextTurn();
        assertTrue(isCurrentPlayer("pallo"));
    }

    @Test
    void testGetGameWinners() throws PlayerNameNotFoundException, TileCacheEmptyException {
        controller.addPlayer("caio");
        controller.addPlayer("sempronio");
        trivialPlayer();
//        System.out.println(controller.getScores());
        ArrayList<String> winners = new ArrayList<>(List.of("caio"));
        assertNotEquals(controller.getGameWinners(), winners);
        winners.add("sempronio");
        assertEquals(controller.getGameWinners(), winners);
    }

    @Test
    void testGetWinnersScore() throws PlayerNameNotFoundException, TileCacheEmptyException {
        controller.addPlayer("caio");
        trivialPlayer();
        assertTrue(controller.getScores().stream().allMatch(i -> i <= controller.getWinnersScore()));
    }

    @Test
    void testGetBoardsNicknames() {
        controller.addPlayer("macro");
        controller.addPlayer("chic");
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        List<String> trueNames = new ArrayList<>(Arrays.asList("macro", "chic", "pinco", "pallo"));
        List<String> names = controller.getPlayersNicknames();
        assertEquals(trueNames, names);
    }

    @Test
    void testGetScores() throws PlayerNameNotFoundException, TileCacheEmptyException {
        controller.addPlayer("macro");
        controller.addPlayer("chic");
        trivialPlayer();
        assertTrue(controller.getScores().stream().allMatch(score -> score >= 0));
    }

}
