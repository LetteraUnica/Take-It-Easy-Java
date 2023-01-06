package engine.model.tile;

import java.util.List;

public interface TileInterface {
    public int getIdTile();

    public int getLeftPath();

    public int getRightPath();

    public int getTopPath();

    public List<Integer> getValues();

    public String toString();

    public boolean equals(TileInterface tile);
}
