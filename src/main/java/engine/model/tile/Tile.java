package engine.model.tile;

import java.util.List;

public class Tile implements TileInterface {
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

    @Override
    public int getIdTile() {
        return idTile;
    }

    @Override
    public int getLeftPath() {
        return leftPath;
    }

    @Override
    public int getRightPath() {
        return rightPath;
    }

    @Override
    public int getTopPath() {
        return topPath;
    }

    @Override
    public List<Integer> getValues() {
        return List.of(leftPath, rightPath, topPath);
    }

    @Override
    public String toString() {
        return "\"Tile[idTile" + this.idTile + "leftPath" + this.leftPath + "rightPath" + this.rightPath + "topPath" + this.topPath + "]";
    }

    @Override
    public boolean equals(TileInterface tile) {
        List<Integer> tileValues = tile.getValues();
        if (tileValues.get(0) == leftPath && tileValues.get(1) == rightPath && tileValues.get(2) == topPath) {
            return true;
        }
        else {
            return false;
        }
    }
}
