package utils;

import beans.Tile;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TileGenerator {

    private final TileKeys pathKeys;

    public TileGenerator() {
        this.pathKeys = new Gson().fromJson("{'leftPathKeys': [2, 6, 7], 'rightPathKeys': [3, 4, 8], 'topPathKeys': [1, 5, 9]}", TileKeys.class);
    }

    public String generateTiles() {
        ArrayList<Tile> tileList = new ArrayList<>();
        int id = 0;
        for (int leftValue:this.pathKeys.getLeftPathKeys()) {
            for (int rightValue:this.pathKeys.getRightPathKeys()) {
                for (int topValue:this.pathKeys.getTopPathKeys()){
                    tileList.add(new Tile(id, leftValue, rightValue, topValue));
                    id++;
                }
            }
        }
        return new Gson().toJson(tileList);
    }

    public static void main(String[] args) throws IOException {
        TileGenerator tiles = new TileGenerator();
        String allTilesString = tiles.generateTiles();
        FileWriter file = new FileWriter("/home/marcosicklinger/SDM-final-project/src/main/resources/tiles/tilesJSON");
        file.write(allTilesString);
    }

}
