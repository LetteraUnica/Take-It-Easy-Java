package engine.model.board;
import engine.model.tile.TileInterface;
import exceptions.CellNotAvailableException;
import javafx.geometry.Point2D;

import java.util.List;

public interface BoardInterface {

    public List<TileInterface> getBoard();

    public String getNickname();

    public int getScore();

    public List<Point2D> getEuclideanCoordinates();

    public BoardInterface copy();

    void placeTile(Integer index, TileInterface placedTile) throws CellNotAvailableException;

    public boolean isBoardFull();

    TileInterface getTile(int tileId);
}
