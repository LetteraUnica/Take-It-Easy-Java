package engine.model.board;

import engine.model.tile.TileInterface;

import java.util.List;

public interface BoardInterface {

    public List<TileInterface> getBoard();

    public String getNickname();

    public int getScore();

    public float[][] getEuclideanCoordinates();

}
