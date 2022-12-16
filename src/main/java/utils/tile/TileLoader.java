package utils.tile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import engine.model.tile.Tile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class TileLoader {
    private static final String FILENAME = "src/main/resources/tiles/tileJSON.txt";

    public List<Tile> loadTileList() {
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            TileGenerator tileGenerator = new TileGenerator();
            return tileGenerator.generateTileList();
        }
        Type jsonListType = new TypeToken<List<Tile>>(){}.getType();
        return new Gson().fromJson(file, jsonListType);
    }
}
