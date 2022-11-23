package beans;

import org.junit.jupiter.api.Test;
import utils.Constants;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileListLoaderTest {

    TileListLoader tileListLoader = new TileListLoader();
    private final ArrayList<Tile> tileList;

    public TileListLoaderTest() throws FileNotFoundException {
        String filename = "src/main/resources/tiles/tileJSON.txt";
        tileListLoader.loadTileList(filename);
        this.tileList = tileListLoader.getTileList();
    }

    @Test
    public void testTileListLoader() {
        for (Tile tile:this.tileList) {
            assertTrue(0 <= tile.getIdTile() && tile.getIdTile() <= 26);
            assertTrue(Constants.leftPathValues.contains(tile.getLeftPath()));
            assertTrue(Constants.rightPathValues.contains(tile.getRightPath()));
            assertTrue(Constants.topPathValues.contains(tile.getTopPath()));
        }
    }

    @Test
    public void testMutabilityGetterMethodOutput() {
        int index = ThreadLocalRandom.current().nextInt(0, this.tileList.size() + 1);
        Tile tileToRemove = this.tileList.get(index);
        this.tileList.remove(index);
        assertFalse(this.tileList.contains(tileToRemove));
    }



}
