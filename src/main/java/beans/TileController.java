package beans;
import java.util.*;
import java.util.Random;

public class TileController {

    private final ArrayList<Tile> availableTiles;
    private final Random randomGenerator;

    public TileController()
    {
        availableTiles = new ArrayList<>(new TileListLoader().getTileList());
        randomGenerator = new Random();

    }

    public ArrayList<Tile> getavailableTiles() {return availableTiles;
    }



    public Tile drawTile() {
        int index = randomGenerator.nextInt(availableTiles.size());
        Tile currentTile = availableTiles.get(index);
        availableTiles.remove(index);
        return currentTile;
    }


}
