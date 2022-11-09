package beans;
import java.util.*;

public class TileListLoader {

    private final ArrayList<Tile> allTiles;

    public TileListLoader() {
        allTiles = new ArrayList<>();
    }

    public void loadTileList(String filename) { }

    public ArrayList<Tile> getTileList() { return (ArrayList<Tile>) List.copyOf(allTiles); }
}
