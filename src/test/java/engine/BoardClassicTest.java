package engine;

import engine.model.board.BoardClassic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardClassicTest {

    @Test
    void testBoard() {
        BoardClassic board = new BoardClassic("Boris");

    }
    @Test
    void getEuclideanCoordinates() {
        BoardClassic board = new BoardClassic("Boris");
        float[][] coordinates = board.getEuclideanCoordinates();

    }
}
