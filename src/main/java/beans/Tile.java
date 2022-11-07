package beans;

import java.util.List;

public class Tile {
    private final int idTile;
    private final int leftPath;
    private final int rightPath;
    private final int topPath;

    public Tile(int idTile, int leftPath, int rightPath, int topPath) {
        this.idTile = idTile;
        this.leftPath = leftPath;
        this.rightPath = rightPath;
        this.topPath = topPath;
    }

    public int getIdTile() {
        return idTile;
    }

    public int getLeftPath() {
        return leftPath;
    }

    public int getRightPath() {
        return rightPath;
    }

    public int getTopPath() {
        return topPath;
    }

    public List<Integer> getValues() {
        return List.of(leftPath, rightPath, topPath);
    }
}
