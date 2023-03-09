package engine.model.board;
import engine.model.tile.TileInterface;
import javafx.geometry.Point2D;
import utils.boardutils.CubeCoordinates;
import utils.boardutils.HexagonalGrid;
import exceptions.CellNotAvailableException;
import java.util.*;
import java.util.stream.Collectors;


public class BoardClassic implements BoardInterface {

    private final ArrayList<TileInterface> board;
    private final String nickname;

    public BoardClassic(String nickname) {
        this.board = new ArrayList<>(Collections.nCopies(19,  null));
        this.nickname = nickname;
    }
    public BoardClassic(String nickname, List<TileInterface> board) {
        this.board = (ArrayList<TileInterface>) board;
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
        ArrayList<CubeCoordinates> coordinatesCells = (ArrayList<CubeCoordinates>) new CubeCoordinates(0, 0, 0).navigateSpiral(2);
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
                int finalI = i;
                Set<Integer> path = line.stream().map(item -> this.board.get(cellsMap.get(item)).getValues().get(finalI)).collect(Collectors.toSet());
                if(path.size()==1){
                    score = score + line.size()*path.iterator().next();
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
        ArrayList<CubeCoordinates> coordinatesCells= (ArrayList<CubeCoordinates>) new CubeCoordinates(0,0,0).navigateSpiral(2);
        float[][][] gridNumeric = new HexagonalGrid(3,10).getHexagonalGrid();
        return  coordinatesCells.stream().map(item ->new Point2D(gridNumeric[item.toEuclidean()[0] + 1][item.toEuclidean()[1] + 5][0], gridNumeric[item.toEuclidean()[0] + 1][item.toEuclidean()[1] + 5][1])).toList();
    }

    @Override
    public BoardInterface copy() {
        return new BoardClassic(this.nickname, new ArrayList<>(this.board));
    }

    @Override
    public void placeTile(Integer index, TileInterface placedTile) throws CellNotAvailableException {
        if (this.board.get(index) != null || index >= this.board.size()) {
            throw new CellNotAvailableException();
        }
        this.board.set(index, placedTile);
    }

    @Override
    public boolean isBoardFull() {
        return !this.board.contains(null);
    }
    @Override
    public TileInterface getTile(int tileId) {return board.get(tileId);
    }
}