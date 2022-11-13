package utils;

import beans.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileKeys {

    private final ArrayList<Integer> leftPathKeys;
    private final ArrayList<Integer> rightPathKeys;
    private final ArrayList<Integer> topPathKeys;


    public TileKeys(ArrayList<Integer> leftPathKeys, ArrayList<Integer> rightPathKeys, ArrayList<Integer> topPathKeys) {
        this.leftPathKeys = leftPathKeys;
        this.rightPathKeys = rightPathKeys;
        this.topPathKeys = topPathKeys;
    }

    public ArrayList<Integer> getLeftPathKeys() { return this.leftPathKeys; }

    public ArrayList<Integer> getRightPathKeys() { return this.rightPathKeys; }

    public ArrayList<Integer> getTopPathKeys() { return this.topPathKeys; }

}
