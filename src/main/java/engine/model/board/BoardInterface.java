package engine.model.board;

import engine.model.tile.TileInterface;

import java.util.ArrayList;
import java.util.List;

public interface BoardInterface {

    public List<TileInterface> getBoard();

    public String getNickname();

    public int getScore();

    public List<List<Float>> getEuclideanCoordinates();

}
