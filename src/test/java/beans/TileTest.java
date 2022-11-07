package beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest {
    @Test
    public void testTile() {
        Tile tile = new Tile(1, 1, 2, 3);
        assertEquals(1, tile.getIdTile());
        assertEquals(1, tile.getLeftPath());
        assertEquals(2, tile.getRightPath());
        assertEquals(3, tile.getTopPath());
    }
}
