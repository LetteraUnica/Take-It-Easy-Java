package engine.tile;
import utils.TileLoader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;

public class TileCollection {

    private final ArrayList<Tile> availableTiles;
    private final Random randomGenerator;

    public TileCollection() throws FileNotFoundException {
        TileLoader tileLoader = new TileLoader();
        String filename = "src/main/resources/tiles/tileJSON.txt";
        tileLoader.loadTileList(filename);
        this.availableTiles = new ArrayList<>(tileLoader.getTileList());
        this.randomGenerator = new Random();
    }

    public List<TileInterface> getAvailableTiles() {return new ArrayList<>(this.availableTiles);
    }



    public Tile drawTile() {
        int index = this.randomGenerator.nextInt(this.availableTiles.size());
        Tile currentTile = this.availableTiles.get(index);
        this.availableTiles.remove(index);
        return currentTile;
    }


}
