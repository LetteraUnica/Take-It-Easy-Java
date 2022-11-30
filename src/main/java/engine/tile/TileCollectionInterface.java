package engine.tile;

import java.util.List;

public interface TileCollectionInterface {
    public List<Tile> getAvailableTiles();
    public Tile drawTile();
}
