package beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TileListLoader {

    private ArrayList<Tile> allTiles;

    public TileListLoader() {
        allTiles = new ArrayList<>();
    }

    public void loadTileList(String filename) throws FileNotFoundException {
        BufferedReader file = new BufferedReader(new FileReader(filename));
        Type jsonListType = new TypeToken<List<Map<String, Integer>>>(){}.getType();
        List<Map<String, Integer>> jsonList = new Gson().fromJson(file, jsonListType);
        listMapToTileList(jsonList);
    }

    private void listMapToTileList(List<Map<String, Integer>> jsonList) {
        for (Map<String, Integer> tileMap: jsonList) {
            allTiles.add(new Tile(tileMap.get("idTile"), tileMap.get("leftPath"), tileMap.get("rightPath"), tileMap.get("topPath")));
        }
    }

    public ArrayList<Tile> getTileList() {
        return new ArrayList<>(this.allTiles);
    }
}
