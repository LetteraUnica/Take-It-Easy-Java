package engine.controller;

import engine.model.tile.TileInterface;

public interface GameInterface {

    public TileInterface getCurrentTile();

    public int getCurrentPlayer();

    public void nextTurn();

    public void addPlayer(String newPlayerName);

    public void removePlayer(int playerIndex);

    public int getCurrentPlayerScore();

    public boolean isGameOver();

}
