package utils.boardutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CubeCoordinates implements Comparable<CubeCoordinates> {
    private Integer x;
    private Integer y;
    private Integer z;
    private int hashCode;


    public CubeCoordinates(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.hashCode = Objects.hash(x, y);
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
        CubeCoordinates[] cubeDirectionVectors=new CubeCoordinates[]{new CubeCoordinates(+1, 0, -1), new CubeCoordinates(+1, -1, 0), new CubeCoordinates(0, -1, +1),
                new CubeCoordinates(-1, 0, +1), new CubeCoordinates(-1, +1, 0), new CubeCoordinates(0, +1, -1)};
        return cubeDirectionVectors[direction];
    }

    public CubeCoordinates cubeAdd(CubeCoordinates vec) {
        return new CubeCoordinates(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public CubeCoordinates cubeScale( int factor){
        return new CubeCoordinates(this.getX() * factor, this.getY() * factor, this.getZ() * factor);
    }

    public CubeCoordinates cubeNeighbor(int direction) {
        return cubeAdd( cubeDirection(direction));
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

    public List<CubeCoordinates> navigateRing(int radius) {
        CubeCoordinates center = new CubeCoordinates(this.x,this.y,this.z);
        ArrayList<CubeCoordinates> results = new ArrayList<>();
        center = center.cubeAdd(center.cubeDirection(4).cubeScale(radius));
        for (int i = 0; i < 6; i++) {
                for (int j = 0; j < radius; j++) {
                    results.add(center);
                    center = center.cubeNeighbor(i);
                }
            }
        return results;
    }

    public List<CubeCoordinates> navigateSpiral(int radius){
        CubeCoordinates center = new CubeCoordinates(this.x,this.y,this.z);
        ArrayList<CubeCoordinates> results = new ArrayList<>();
        results.add(center);
        for (int i = 1; i<radius+1;++i) {
            results.addAll(navigateRing(i));
        }
        return results;
    }

    public int[] toEuclidean(){
        int[] coordinates= new int[2];
        if (this.x % 2 == 0){
        coordinates[0]=(this.x)/2;
        coordinates[1]=(this.y+coordinates[0])*2;
        }
        else{
            coordinates[0]=(this.x-1)/2;
            coordinates[1]=(this.y+coordinates[0]+1)*2-1;
        }
        return coordinates;

    }

    @Override
    public int compareTo(CubeCoordinates cubeCoordinates) {
        return  Math.abs(this.x-cubeCoordinates.getX()) +Math.abs(this.y- cubeCoordinates.getY());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CubeCoordinates that = (CubeCoordinates) o;
        return x == that.x && y == that.y;
    }
    @Override
    public int hashCode() {
        return this.hashCode;
    }
}





