package engine.model.board;

import engine.model.tile.TileInterface;
import utils.CubeCoordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardClassic implements BoardInterface {

    private ArrayList<TileInterface> board;
    private final String nickname;

    public BoardClassic(String nickname) {
        this.board = new ArrayList<>(Collections.nCopies(19,  null));
        this.nickname = nickname;
    }



    @Override
    public ArrayList<TileInterface> getBoard() {
        return board;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public float[][][] getEuclideanCoordinates() {
        CubeCoordinates center= new CubeCoordinates(0,0,0);
        ArrayList coordinatesCells= (ArrayList) center.navigateSpiral(3);

        return ;
    }


    public float[][][] mapEuclideanCoordinatesToHexagonal(int width, int length) {
        float[][][] grid = initializeEuclideanGrid(width, length);
        for (int i = 0; i < width; ++i) {
            for(int j = 0; j < length; ++j) {
                System.out.print("x: ");
                System.out.print(grid[i][j][0]);
                System.out.println("");
                System.out.print("y: ");
                System.out.print(grid[i][j][1]);
                System.out.println("");

            }
        }
        for (int i = 0; i < width; ++i) {
            for(int j = 0; j < length; ++j) {
                if (j%2==0){
                grid[i][j][0] = grid[i][j][0]*2;
                grid[i][j][1] = grid[i][j][1]/2 - i;
                }

            }
        }
        for (int i = 0; i < width; ++i) {
            for(int j = 0; j < length; ++j) {
                if (j%2!=0){
                    grid[i][j][0] = grid[i][j-1][0]+1;
                    grid[i][j][1] = grid[i][j-1][1]/2-1;
                }

            }
        }
        return grid;
    }


    public float[][][] getHexagonalGrid(int width, int length){
        //float[][][] grid = initializeEuclideanGrid(width, length);
        //grid = gridCenter(grid);
        //grid = gridHexagonalise(grid);
        //grid = gridNormalize(grid);
        float[][][] grid = mapEuclideanCoordinatesToHexagonal(width, length);

        for (int i = 0; i < width; ++i) {
            for(int j = 0; j < length; ++j) {
                System.out.print("x: ");
                System.out.print(grid[i][j][0]);
                System.out.println("");
                System.out.print("y: ");
                System.out.print(grid[i][j][1]);
                System.out.println("");

            }
        }
        return grid;
    }

    public float mapBoardToHexagonalCoordinates(){
        return 0;
    }

    public float[][][] initializeEuclideanGrid(int width, int length){
        float[][][] grid = new float[width][length][2];
        for (int i = 0; i < width; ++i) {
            for(int j = 0; j < length; ++j) {

                grid[i][j][0] = i;
                grid[i][j][1] = j;
            }
        }
        return grid;
    }


}
