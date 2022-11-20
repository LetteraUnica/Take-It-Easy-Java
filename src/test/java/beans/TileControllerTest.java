package beans;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TileControllerTest {

    @Test
    public void testTileController() {
        TileController tilecontroller = new TileController();
        ArrayList<Tile> testTiles = new TileListLoader().getTileList();
        assertEquals(testTiles, tilecontroller.getavailableTiles());
    }

    @Test
    public void testTileControllerLength() {
        TileController tilecontroller = new TileController();
        ArrayList<Tile> testTiles = new TileListLoader().getTileList();
        assertEquals(testTiles.size(), tilecontroller.getavailableTiles().size());
    }
}

    /*

    @Test
    public void testTileControllerLengthDraw() {
        TileController tilecontroller = new TileController();
        Tile testTile = tilecontroller.drawTile();
        ArrayList<Tile> testTiles = new TileListLoader().getTileList();
        assertEquals(testTiles.size() -1 , tilecontroller.getavailableTiles().size());

    }

    @Test
    public void testDrawTile() {
        TileController tilecontroller = new TileController();
        Tile currentTile = tilecontroller.drawTile();
        assertNotNull(currentTile);
    }
}
*/
