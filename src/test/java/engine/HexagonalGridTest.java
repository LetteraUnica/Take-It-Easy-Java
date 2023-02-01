package engine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.boardutils.HexagonalGrid;


class HexagonalGridTest {


    @Test
    void testInitializeEuclideanGrid() {
        HexagonalGrid hexagonalGrid = new HexagonalGrid(4, 4);
        float[][][] expectedResult = {{{0f, 0f}, {0f, 1f}, {0f, 2f},{0f, 3f}},
                {{1f, 0f}, {1f, 1f}, {1f, 2f},{1f, 3f}},
                {{2f, 0f}, {2f, 1f}, {2f, 2f},{2f, 3f}},
                {{3f, 0f}, {3f, 1f}, {3f, 2f},{3f, 3f}}};
        assertArrayEquals(expectedResult, hexagonalGrid.initializeEuclideanGrid());
    }


    @Test
    void testGridCenter() {
        HexagonalGrid hexagonalGrid = new HexagonalGrid(3,3);
        float[][][] expected = {{{-1f, -1f}, {-1f, 0f}, {-1f, 1f}}, {{0f, -1f}, {0f, 0f}, {0f, 1f}},{{1f, -1f}, {1f, 0f}, {1f, 1f}}};
        float[][][] result = hexagonalGrid.gridCenter(hexagonalGrid.initializeEuclideanGrid());
        assertArrayEquals(expected, result);
    }

    @Test
    void testGridHexagonalise() {
        HexagonalGrid hexagonalGrid = new HexagonalGrid(2, 2);
        float[][][] expectedResult = {{{0.5f, 0f}, {0f, 0.28867513f}},
                {{1.5f, 0f}, {1f, 0.28867513f}}};
        assertArrayEquals(expectedResult, hexagonalGrid.gridHexagonalise(hexagonalGrid.initializeEuclideanGrid()));
    }

    @Test
    void testGridNormalize() {
        HexagonalGrid hexagonalGrid = new HexagonalGrid(3, 3);
        float[][][] expected = {{{-1f, -1f}, {-1f, 0f}, {-1f, 1f}}, {{0f, -1f}, {0f, 0f}, {0f, 1f}}, {{1f, -1f}, {1f, 0f}, {1f, 1f}}};
        float[][][] result = hexagonalGrid.gridNormalize(hexagonalGrid.gridCenter(hexagonalGrid.initializeEuclideanGrid()));
        assertArrayEquals(expected, result);
    }

    @Test
    void testGetHexagonalGrid() {
        HexagonalGrid grid = new HexagonalGrid(2, 2);
        float[][][] expectedGrid = {{{-1f, -0.57735026f}, {-2f, 0f}},
                {{1f, -0.57735026f}, {0f, 0f}}};
        float[][][] actualGrid = grid.getHexagonalGrid();
        assertArrayEquals(expectedGrid, actualGrid);
    }
}
