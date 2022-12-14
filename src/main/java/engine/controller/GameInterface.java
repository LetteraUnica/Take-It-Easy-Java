package engine.controller;

import engine.model.tile.TileInterface;

public interface GameInterface {

    public TileInterface getCurrentTile();

    public int getCurrentPlayer();

    public void nextTurn();

    public void addPlayer(String newPlayerName);

    public void removePlayer();

    public int getPlayerScore();

    public boolean isGameOver();

}
