package engine.model.board;


import engine.model.tile.TileInterface;
import javafx.geometry.Point2D;
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


        int score = 0;
        for (int i =0; i<3;++i) {

            HashSet<CubeCoordinates> availableCells = new HashSet<>(cellsMap.keySet());




            while (!availableCells.isEmpty()) {
                ArrayList<CubeCoordinates> line = new ArrayList<>();
                CubeCoordinates pickedCell = availableCells.iterator().next();
                line.add(pickedCell);
                availableCells.remove(pickedCell);
                exploreDirection(line,pickedCell.cubeNeighbor(i), i, availableCells, cellsMap);
                exploreDirection(line,pickedCell.cubeNeighbor(i+3), i+3, availableCells, cellsMap);

                HashSet<Integer> paths = new HashSet<>();

                for (CubeCoordinates cubeCoordinates : line) {
                    paths.add(this.board.get(cellsMap.get(cubeCoordinates)).getValues().get(i));
                    }
                if(paths.size()==1){
                    score = score + line.size()*paths.iterator().next();
                }

                }
            }
        return score;
    }


    public void exploreDirection(List<CubeCoordinates> line,CubeCoordinates currentCell, int direction, Set<CubeCoordinates> availableCells, Map<CubeCoordinates, Integer> cellsMap){
        if (cellsMap.containsKey(currentCell)){
        line.add(currentCell);
        availableCells.remove(currentCell);
        currentCell = currentCell.cubeNeighbor(direction);
        exploreDirection(line, currentCell, direction, availableCells, cellsMap);
        }

    }

    @Override
    public List<Point2D> getEuclideanCoordinates() {
        CubeCoordinates center= new CubeCoordinates(0,0,0);
        ArrayList<CubeCoordinates> coordinatesCells= (ArrayList<CubeCoordinates>) center.navigateSpiral(2);
        HexagonalGrid gridShape = new HexagonalGrid(3,10);
        float[][][] gridNumeric = gridShape.getHexagonalGrid();
        List<Point2D> displayCoordinates = new ArrayList<>();
        for (CubeCoordinates cell : coordinatesCells) {
            displayCoordinates.add(new Point2D(gridNumeric[cell.toEuclidean()[0] + 1][cell.toEuclidean()[1] + 5][0], gridNumeric[cell.toEuclidean()[0] + 1][cell.toEuclidean()[1] + 5][1]));
        }
        return displayCoordinates;
    }

    @Override
    public BoardInterface copy() {
        BoardInterface copiedBoard = new BoardClassic(this.nickname);
        for (int i=0; i<this.board.size(); ++i){
            copiedBoard.placeTile(i,  this.board.get(i));
        }
        return copiedBoard;
    }

    @Override
    public void placeTile(Integer index, TileInterface placedTile) {
        this.board.set(index, placedTile);
    }

    @Override
    public boolean isBoardFull() {
        for (TileInterface tileInterface : this.board) {
            if (tileInterface == null) {
                return false;
            }
        }
        return true;

    }

    @Override
    public TileInterface getTile(int tileId) {
        return board.get(tileId);
    }

}
