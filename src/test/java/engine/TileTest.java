package engine;

import engine.model.tile.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TileTest {
    @Test
    void testTile() {
        Tile tile = new Tile(1, 2, 3, 4);
        assertEquals(1, tile.getIdTile());
        assertEquals(2, tile.getRightPath());
        assertEquals(3, tile.getLeftPath());
        assertEquals(4, tile.getTopPath());
    }

    @Test
    void testTileValues() {
        Tile tile = new Tile(1, 2, 3, 4);
        assertEquals(List.of(2, 3, 4), tile.getValues());
    }

    @Test
    void testImmutability1() {
        Tile tile = new Tile(1, 2, 3, 4);
        assertThrows(UnsupportedOperationException.class, () -> tile.getValues().add(5));
    }

    @Test
    void testImmutability2() {
        Tile tile = new Tile(1, 2, 3, 4);
        ArrayList<Integer> values = new ArrayList<>(tile.getValues());
        values.add(5);
        assertEquals(List.of(2, 3, 4), tile.getValues());
    }
}
