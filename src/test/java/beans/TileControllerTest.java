package beans;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TileControllerTest {


    @Test
    public void testTileController() throws FileNotFoundException {
        TileController tilecontroller = new TileController();


        String filename = "src/main/resources/tiles/tileJSON.txt";
        TileListLoader tileListLoader = new TileListLoader();
        tileListLoader.loadTileList(filename);
        ArrayList<Tile> testTiles = tileListLoader.getTileList();


        System.out.println(testTiles);
        System.out.println(tilecontroller.getavailableTiles());
        assertEquals(testTiles, tilecontroller.getavailableTiles());
    }



    @Test
    public void testTileControllerLengthDraw() throws FileNotFoundException {
        TileController tilecontroller0 = new TileController();
        TileController tilecontroller1 = new TileController();
        Tile testTile = tilecontroller1.drawTile();
        assertEquals(tilecontroller0.getavailableTiles().size()  , tilecontroller1.getavailableTiles().size()+1);
    }

    @Test
    public void testDrawTile() throws FileNotFoundException {
        TileController tilecontroller = new TileController();
        Tile currentTile = tilecontroller.drawTile();
        assertNotNull(currentTile);
    }
}

