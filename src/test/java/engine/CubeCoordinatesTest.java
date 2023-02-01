package engine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.boardutils.CubeCoordinates;
import java.util.ArrayList;
import java.util.List;

class CubeCoordinatesTest {

    @Test
    void testGetX() {
        CubeCoordinates coord = new CubeCoordinates(1, 2, 3);
        assertEquals(1, coord.getX());
    }

    @Test
    void testGetY() {
        CubeCoordinates coord = new CubeCoordinates(1, 2, 3);
        assertEquals(2, coord.getY());
    }

    @Test
    void testGetZ() {
        CubeCoordinates coord = new CubeCoordinates(1, 2, 3);
        assertEquals(3, coord.getZ());
    }

    @Test
    void testCubeDirection() {
        CubeCoordinates coord = new CubeCoordinates(0, 0, 0);
        CubeCoordinates direction = coord.cubeDirection(0);
        assertEquals(1, direction.getX());
        assertEquals(0, direction.getY());
        assertEquals(-1, direction.getZ());
    }

    @Test
    void testCubeAdd() {
        CubeCoordinates coord1 = new CubeCoordinates(1, 2, 3);
        CubeCoordinates coord2 = new CubeCoordinates(4, 5, 6);
        CubeCoordinates result = coord1.cubeAdd(coord2);
        assertEquals(5, result.getX());
        assertEquals(7, result.getY());
        assertEquals(9, result.getZ());
    }

    @Test
    void testCubeScale() {
        CubeCoordinates coord = new CubeCoordinates(1, 2, 3);
        CubeCoordinates result = coord.cubeScale(2);
        assertEquals(2, result.getX());
        assertEquals(4, result.getY());
        assertEquals(6, result.getZ());
    }

    @Test
    void testCubeNeighbor() {
        CubeCoordinates coord = new CubeCoordinates(1, 2, 3);
        CubeCoordinates result = coord.cubeNeighbor(0);
        assertEquals(2, result.getX());
        assertEquals(2, result.getY());
        assertEquals(2, result.getZ());
    }

    @Test
    void testRotateLeft() {
        CubeCoordinates coord = new CubeCoordinates(1, 2, 3);
        CubeCoordinates result = coord.rotateLeft();
        assertEquals(2, result.getX().intValue());
        assertEquals(3, result.getY().intValue());
        assertEquals(1, result.getZ().intValue());
    }

    @Test
    void testNavigateRing() {
        CubeCoordinates center = new CubeCoordinates(0, 0, 0);
        int radius = 2;
        List<CubeCoordinates> result = center.navigateRing(radius);
        List<CubeCoordinates> expected = new ArrayList<>(List.of(
                new CubeCoordinates(-2, 2, 0),
                new CubeCoordinates(-1, 2, -1),
                new CubeCoordinates(0, 2, -2),
                new CubeCoordinates(1, 1, -2),
                new CubeCoordinates(2, 0, -2),
                new CubeCoordinates(2, -1, -1),
                new CubeCoordinates(2, -2, 0),
                new CubeCoordinates(1, -2, 1),
                new CubeCoordinates(0, -2, 2),
                new CubeCoordinates(-1, -1, 2),
                new CubeCoordinates(-2, 0, 2),
                new CubeCoordinates(-2, 1, 1)

        ));
        assertEquals(expected, result);
    }

    @Test
    void testNavigateSpiral() {
        CubeCoordinates center = new CubeCoordinates(0, 0, 0);
        int radius = 2;
        List<CubeCoordinates> result = center.navigateSpiral(radius);
        List<CubeCoordinates> expected = new ArrayList<>(List.of(
                new CubeCoordinates(0, 0, 0),
                new CubeCoordinates(-1, 1, 0),
                new CubeCoordinates(0, 1, -1),
                new CubeCoordinates(1, 0, -1),
                new CubeCoordinates(1, -1, 0),
                new CubeCoordinates(0, -1, 1),
                new CubeCoordinates(-1, 0, 1),
                new CubeCoordinates(-2, 2, 0),
                new CubeCoordinates(-1, 2, -1),
                new CubeCoordinates(0, 2, -2),
                new CubeCoordinates(1, 1, -2),
                new CubeCoordinates(2, 0, -2),
                new CubeCoordinates(2, -1, -1),
                new CubeCoordinates(2, -2, 0),
                new CubeCoordinates(1, -2, 1),
                new CubeCoordinates(0, -2, 2),
                new CubeCoordinates(-1, -1, 2),
                new CubeCoordinates(-2, 0, 2),
                new CubeCoordinates(-2, 1, 1)
        ));
        assertEquals(expected, result);
    }

    @Test
    void testToEuclidean() {
        CubeCoordinates evenXCoords = new CubeCoordinates(2, 1, -3);
        int[] expectedEvenXResult = new int[] {1, 4};
        assertArrayEquals(expectedEvenXResult, evenXCoords.toEuclidean());

        CubeCoordinates oddXCoords = new CubeCoordinates(3, 2, -5);
        int[] expectedOddXResult = new int[] {1, 7};
        assertArrayEquals(expectedOddXResult, oddXCoords.toEuclidean());
    }

    @Test
    void testCompareTo() {
        CubeCoordinates cube1 = new CubeCoordinates(1, 2, -3);
        CubeCoordinates cube2 = new CubeCoordinates(0, 0, 0);
        CubeCoordinates cube3 = new CubeCoordinates(1, 2, -3);
        assertTrue(cube1.compareTo(cube2) > 0);
        assertEquals(0, cube3.compareTo(cube1));
    }

    @Test
    void testEqualsMethod() {
        CubeCoordinates cubeCoordinates1 = new CubeCoordinates(1, 2, 3);
        CubeCoordinates cubeCoordinates2 = new CubeCoordinates(1, 2, 3);
        CubeCoordinates cubeCoordinates3 = new CubeCoordinates(2, 2, 3);

        assertTrue(cubeCoordinates1.equals(cubeCoordinates2));
        assertFalse(cubeCoordinates1.equals(cubeCoordinates3));
        assertFalse(cubeCoordinates1.equals(null));
        assertFalse(cubeCoordinates1.equals("Not a CubeCoordinates object"));
    }
}
