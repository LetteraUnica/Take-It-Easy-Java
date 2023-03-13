package engine;

import engine.controller.GameController;
import engine.controller.GameControllerInterface;
import engine.model.board.BoardInterface;
import exceptions.NoBoardFoundException;
import exceptions.PlayerNameNotFoundException;
import exceptions.TileCacheEmptyException;
import org.junit.jupiter.api.Test;
import utils.tile.Constants;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameControllerInterface controller = new GameController();

    private void trivialGameFinisher() throws TileCacheEmptyException {
        int place = 0;
        while (!controller.isGameOver()) {
            for (int playerIndex = 0; playerIndex < controller.getPlayersNicknames().size(); playerIndex++) {
                controller.placeTileIn(place);
                if (controller.isGameOver()) {
                    break;
                }
                controller.nextTurn();
            }
            place++;
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
    void testIsGameOver() throws TileCacheEmptyException, PlayerNameNotFoundException {
        controller.addPlayer("pincopallo");
        int i = 0;
        while (!controller.isGameOver()) {
            controller.placeTileIn(i);
            controller.nextTurn();
            i++;
        }
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
    void testGetGameWinners() throws TileCacheEmptyException {
        controller.addPlayer("caio");
        controller.addPlayer("sempronio");
        trivialGameFinisher();
        ArrayList<String> winners = new ArrayList<>(List.of("caio"));
        assertNotEquals(controller.getGameWinners(), winners);
        winners.add("sempronio");
        assertEquals(controller.getGameWinners(), winners);
    }

    @Test
    void testGetWinnersScore() throws TileCacheEmptyException {
        controller.addPlayer("caio");
        trivialGameFinisher();
        assertTrue(controller.getScores().stream().allMatch(i -> i <= controller.getWinnersScore()));
    }

    @Test
    void testGetBoardsNicknames() {
        controller.addPlayer("macro");
        controller.addPlayer("chic");
        controller.addPlayer("pinco");
        controller.addPlayer("pallo");
        List<String> trueNames = new ArrayList<>(asList("macro", "chic", "pinco", "pallo"));
        List<String> names = controller.getPlayersNicknames();
        assertEquals(trueNames, names);
    }

    @Test
    void testGetScores() throws TileCacheEmptyException {
        controller.addPlayer("macro");
        controller.addPlayer("chic");
        trivialGameFinisher();
        assertTrue(controller.getScores().stream().allMatch(score -> (score >= 0 && score <= Constants.BESTSCORE)));
    }

}
