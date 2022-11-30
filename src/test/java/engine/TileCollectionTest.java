package engine;

import engine.tile.Tile;
import engine.tile.TileCollection;
import utils.TileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TileCollectionTest {


    @Test
    public void testTileController() throws FileNotFoundException {
        TileCollection tilecontroller = new TileCollection();


        String filename = "src/main/resources/tiles/tileJSON.txt";
        TileLoader tileLoader = new TileLoader();
        tileLoader.loadTileList(filename);
        ArrayList<Tile> testTiles = tileLoader.getTileList();


        System.out.println(testTiles);
        System.out.println(tilecontroller.getAvailableTiles());
        assertEquals(testTiles, tilecontroller.getAvailableTiles());
    }



    @Test
    public void testTileControllerLengthDraw() throws FileNotFoundException {
        TileCollection tilecontroller0 = new TileCollection();
        TileCollection tilecontroller1 = new TileCollection();
        Tile testTile = tilecontroller1.drawTile();
        assertEquals(tilecontroller0.getAvailableTiles().size()  , tilecontroller1.getAvailableTiles().size()+1);
    }

    @Test
    public void testDrawTile() throws FileNotFoundException {
        TileCollection tilecontroller = new TileCollection();
        Tile currentTile = tilecontroller.drawTile();
        assertNotNull(currentTile);
    }
}

