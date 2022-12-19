package utils;

import com.google.gson.Gson;
import engine.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileGenerator {
    public String generateTileGson() {
        List<Tile> tileList = generateTileList();
        return new Gson().toJson(tileList);
    }

    public ArrayList<Tile> generateTileList() {
        ArrayList<Tile> tileList = new ArrayList<>();
        int id = 0;
        for (int leftValue : Constants.leftPathValues) {
            for (int rightValue : Constants.rightPathValues) {
                for (int topValue : Constants.topPathValues) {
                    tileList.add(new Tile(id, leftValue, rightValue, topValue));
                    id++;
                }
            }
        }
        return tileList;
    }
}
