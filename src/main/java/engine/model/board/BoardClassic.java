package engine.model.board;

import engine.model.tile.TileInterface;
import utils.boardUtils.CubeCoordinates;
import utils.boardUtils.HexagonalGrid;

import java.util.ArrayList;
import java.util.Collections;


public class BoardClassic implements BoardInterface {

    private final ArrayList<TileInterface> board;
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
    public float[][] getEuclideanCoordinates() {
        CubeCoordinates center= new CubeCoordinates(0,0,0);

        ArrayList<CubeCoordinates> coordinatesCells= (ArrayList<CubeCoordinates>) center.navigateSpiral(2);
        HexagonalGrid gridShape = new HexagonalGrid(3,10);
        float[][][] gridNumeric = gridShape.getHexagonalGrid();

        float[][] displayCoordinates = new float[coordinatesCells.size()][2];
        for (int i=0; i< coordinatesCells.size(); ++i){
            CubeCoordinates cell = coordinatesCells.get(i);
            for (int j = 0; j < 2; j++)
                displayCoordinates[i][j] = gridNumeric[cell.toEuclidean()[0]+1][ cell.toEuclidean()[1]+5][j];
        }
        return displayCoordinates;
    }
    
}
