package utils;

import engine.tile.Tile;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TileGenerator {

    public String generateTiles() {
        ArrayList<Tile> tileList = new ArrayList<>();
        int id = 0;
        for (int leftValue:Constants.leftPathValues) {
            for (int rightValue:Constants.rightPathValues) {
                for (int topValue:Constants.topPathValues){
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
        System.out.print(allTilesString);
        try (PrintWriter out = new PrintWriter("src/main/resources/tiles/tileJSON.txt")) {
            out.println(allTilesString);
        }
    }

}
