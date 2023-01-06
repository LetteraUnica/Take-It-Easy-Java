package engine;

import engine.controller.GameController;
import engine.controller.GameInterface;
import engine.model.tile.Tile;
import exceptions.FatalGameErrorException;
import org.junit.jupiter.api.Test;
import utils.tile.TileLoader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameControllerTest {

    GameInterface controller = new GameController();

    public GameControllerTest() throws FatalGameErrorException {}

    @Test
    void testIsGameOver() {
        assertFalse(controller.isGameOver());
        int numberOfTiles = ((ArrayList<Tile>) new TileLoader().loadTileList()).size();
        System.out.println(numberOfTiles);
        for (int i = 0; i < numberOfTiles; i++) {
            controller.nextTurn();
        }
        assertTrue(controller.isGameOver());
    }

}
