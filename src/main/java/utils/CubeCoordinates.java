package utils;


import engine.model.board.BoardClassic;

import java.util.ArrayList;
import java.util.List;

public class CubeCoordinates {
    private Integer x, y, z;
    private final CubeCoordinates[] cubeDirectionVectors = new CubeCoordinates[]{new CubeCoordinates(+1, 0, -1), new CubeCoordinates(+1, -1, 0), new CubeCoordinates(0, -1, +1),
            new CubeCoordinates(-1, 0, +1), new CubeCoordinates(-1, +1, 0), new CubeCoordinates(0, +1, -1)};

    public CubeCoordinates(Integer x, Integer y, Integer z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public CubeCoordinates cubeDirection(int direction) {
        return this.cubeDirectionVectors[direction];
    }

    public CubeCoordinates cubeAdd(CubeCoordinates hex, CubeCoordinates vec) {
        return new CubeCoordinates(hex.x + vec.x, hex.y + vec.y, hex.z + vec.z);
    }

    public CubeCoordinates cubeNeighbor(CubeCoordinates cube, int direction) {
        return cubeAdd(cube, cubeDirection(direction));
    }

    public CubeCoordinates rotateLeft() {
        Integer buffer = x;
        x = y;
        y = z;
        z = buffer;

        return this;
    }

    public CubeCoordinates rotateRight() {
        Integer buffer = x;
        x = z;
        z = y;
        y = buffer;

        return this;
    }

    public List<CubeCoordinates> navigateSpiral(int radius) {
        CubeCoordinates center = new CubeCoordinates(this.x,this.y,this.z);
        ArrayList<CubeCoordinates> results = new ArrayList<>();
        for (int r=0; r<radius+1; r++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < r; j++) {
                    results.add(center);
                    center = cubeNeighbor(center, i);
                }
            }
        }
        return results;

    }

}





