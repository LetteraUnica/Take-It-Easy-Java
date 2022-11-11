package beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundTest {
    @Test
    public void testRound() {
        Tile tile = new Tile(1,2,3,4);
        Round round = new Round(1, tile);
        assertEquals(1, round.getcurrentPlayer());
        assertEquals(tile, round.getcurrentTile());
    }

}