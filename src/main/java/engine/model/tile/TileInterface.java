package engine.model.tile;

import java.util.List;

public interface TileInterface {
    int getIdTile();

    int getLeftPath();

    int getRightPath();

    int getTopPath();

    List<Integer> getValues();

    String toString();

    boolean equals(Object tile);
}
