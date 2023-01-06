package engine.model.board;

import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import javafx.geometry.Point2D;

import java.util.List;

public interface BoardInterface {

    public List<TileInterface> getBoard();

    public String getNickname();

    public int getScore();

    public List<Point2D> getEuclideanCoordinates();

    public BoardInterface copy();


    void placeTile(Integer index, TileInterface placedTile);

    public boolean isBoardFull();

}
