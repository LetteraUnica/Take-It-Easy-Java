package engine;

import engine.model.tile.Tile;
import org.junit.jupiter.api.Test;
import utils.Constants;
import utils.TileLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileListLoaderTest {

    TileLoader tileLoader = new TileLoader();
    private final List<Tile> tileList = tileLoader.loadTileList();

    @Test
    void testTileListLoader() {
        for (Tile tile:this.tileList) {
            assertTrue(0 <= tile.getIdTile() && tile.getIdTile() <= 26);
            assertTrue(Constants.leftPathValues.contains(tile.getLeftPath()));
            assertTrue(Constants.rightPathValues.contains(tile.getRightPath()));
            assertTrue(Constants.topPathValues.contains(tile.getTopPath()));
        }
    }

    @Test
    void testMutabilityGetterMethodOutput() {
        int index = ThreadLocalRandom.current().nextInt(0, this.tileList.size() + 1);
        Tile tileToRemove = this.tileList.get(index);
        this.tileList.remove(index);
        assertFalse(this.tileList.contains(tileToRemove));
    }
}
