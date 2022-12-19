package engine;

import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import engine.state.MatchState;
import exceptions.FatalGameErrorException;
import org.junit.jupiter.api.Test;
import utils.TileLoader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchStateTest {

    MatchState state = new MatchState();

    public MatchStateTest() throws FatalGameErrorException {}

    @Test
    void testMatchStateAvailableTiles() {
        ArrayList<Tile> tileList = (ArrayList<Tile>) new TileLoader().loadTileList();
        assertEquals(tileList.size(), state.getCacheSize());
    }



}
