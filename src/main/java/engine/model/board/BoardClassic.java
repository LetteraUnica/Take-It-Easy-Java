package engine.model.board;

import engine.model.tile.TileInterface;
import utils.boardutils.CubeCoordinates;
import utils.boardutils.HexagonalGrid;

import java.util.*;


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
        CubeCoordinates center = new CubeCoordinates(0, 0, 0);
        ArrayList<CubeCoordinates> coordinatesCells = (ArrayList<CubeCoordinates>) center.navigateSpiral(2);
        HashMap<CubeCoordinates, Integer> cellsMap = new HashMap<>();


        for (int j = 0; j < this.board.size(); ++j) {
            cellsMap.put(coordinatesCells.get(j), j);
        }

        for (int i =0; i<3;++i) {
            NavigableSet<CubeCoordinates> availableCells = (NavigableSet<CubeCoordinates>) cellsMap.keySet();
            while (0 < availableCells.size()) {
                ArrayList<CubeCoordinates> line = new ArrayList<>();
                CubeCoordinates pickedCell = availableCells.first();
                line.add(pickedCell);
                cellsMap.remove(pickedCell);

            }
        }
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
