package beans;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TileTest {
    @Test
    public void testTile() {
        Tile tile = new Tile(1, 2, 3, 4);
        assertEquals(1, tile.getIdTile());
        assertEquals(2, tile.getLeftPath());
        assertEquals(3, tile.getRightPath());
        assertEquals(4, tile.getTopPath());
    }

    @Test
    public void testTileValues() {
        Tile tile = new Tile(1, 2, 3, 4);
        assertEquals(List.of(2, 3, 4), tile.getValues());
    }

    @Test
    public void testImmutability1() {
        Tile tile = new Tile(1, 2, 3, 4);
        assertThrows(UnsupportedOperationException.class, () -> tile.getValues().add(5));
    }

    @Test
    public void testImmutability2() {
        Tile tile = new Tile(1, 2, 3, 4);
        ArrayList<Integer> values = new ArrayList<>(tile.getValues());
        values.add(5);
        assertEquals(List.of(2, 3, 4), tile.getValues());
    }
}
