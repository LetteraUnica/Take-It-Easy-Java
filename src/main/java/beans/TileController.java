package beans;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;

public class TileController {

    private final ArrayList<Tile> availableTiles;
    private final Random randomGenerator;

    public TileController() throws FileNotFoundException {
        TileListLoader tileListLoader = new TileListLoader();
        String filename = "src/main/resources/tiles/tileJSON.txt";
        tileListLoader.loadTileList(filename);
        this.availableTiles = new ArrayList<>(tileListLoader.getTileList());
        this.randomGenerator = new Random();
    }

    public ArrayList<Tile> getavailableTiles() {return new ArrayList<>(this.availableTiles);
    }



    public Tile drawTile() {
        int index = this.randomGenerator.nextInt(this.availableTiles.size());
        Tile currentTile = this.availableTiles.get(index);
        this.availableTiles.remove(index);
        return currentTile;
    }


}
