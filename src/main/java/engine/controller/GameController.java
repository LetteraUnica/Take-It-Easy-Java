package engine.controller;

import engine.model.tile.TileInterface;
import engine.state.MatchState;
import exceptions.FatalGameErrorException;

public class GameController implements GameInterface {

    private MatchState matchState = new MatchState();

    public GameController() throws FatalGameErrorException {}

    @Override
    public TileInterface getCurrentTile() {
        return matchState.getCurrentTile();
    }

    @Override
    public int getCurrentPlayer() {
        return matchState.getCurrentPlayer();
    }

    @Override
    public void nextTurn() {
        matchState.drawTile();
        matchState.nextPlayer();
    }

    @Override
    public void addPlayer(String newPlayerName) {

    }

    @Override
    public void removePlayer() {

    }

    @Override
    public int getPlayerScore() {
        return 0;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
