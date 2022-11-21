package beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TileListLoader {

    private ArrayList<Tile> allTiles;

    public TileListLoader() {
        allTiles = new ArrayList<>();
    }

    public void loadTileList(String filename) throws FileNotFoundException {
        BufferedReader file = new BufferedReader(new FileReader(filename));
        Type jsonListType = new TypeToken<List<Tile>>(){}.getType();
        this.allTiles = new Gson().fromJson(file, jsonListType);
    }

    public ArrayList<Tile> getTileList() {
        return new ArrayList<>(this.allTiles);
    }
}
