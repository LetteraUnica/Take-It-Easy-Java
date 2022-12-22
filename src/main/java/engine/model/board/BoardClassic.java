package engine.model.board;

import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import utils.boardutils.CubeCoordinates;
import utils.boardutils.HexagonalGrid;

import java.awt.geom.Point2D;
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

        int score = 0;
        for (int i =0; i<3;++i) {
            TreeSet<CubeCoordinates> availableCells = new TreeSet<>(cellsMap.keySet());
            while (!availableCells.isEmpty()) {
                ArrayList<CubeCoordinates> line = new ArrayList<>();
                CubeCoordinates pickedCell = availableCells.first();
                line.add(pickedCell);
                availableCells.remove(pickedCell);
                exploreDirection(line,pickedCell, i, availableCells, cellsMap);
                exploreDirection(line,pickedCell, i*2, availableCells, cellsMap);
                TreeSet<Integer> paths = new TreeSet<>();
                for (CubeCoordinates cubeCoordinates : line) {
                    paths.add(this.board.get(cellsMap.get(cubeCoordinates)).getRightPath());
                    }
                if(paths.size()==1){
                    score = score + line.size()*paths.first();
                }
                }
            }
        return score;
    }


    public void exploreDirection(List<CubeCoordinates> line,CubeCoordinates currentCell, int direction, SortedSet<CubeCoordinates> availableCells, Map<CubeCoordinates, Integer> cellsMap){
        if (cellsMap.containsKey(currentCell)){
        line.add(currentCell);
        availableCells.remove(currentCell);
        currentCell = currentCell.cubeNeighbor(direction);
        exploreDirection(line, currentCell, direction, availableCells, cellsMap);}
    }

    @Override
    public List<Point2D> getEuclideanCoordinates() {
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

    @Override
    public BoardInterface copy() {

        return null;
    }


    @Override
    public void placeTile(Integer index, Tile placedTile) {
        this.board.set(index, placedTile);
    }

}
