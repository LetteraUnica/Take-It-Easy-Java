package engine.controller;

import engine.model.board.Board;
import engine.model.board.BoardInterface;
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
//        BoardInterface newBoard = new Board(newPlayerName);
//        matchState.addBoard(newBoard);
    }

    @Override
    public void removePlayer(int playerIndex) {
        matchState.deleteBoard(playerIndex);
    }

    @Override
    public int getCurrentPlayerScore() {
        return matchState.getCurrentPlayerScore();
    }

    @Override
    public boolean isGameOver() {
        return matchState.getCacheSize() == 0;
    }
}
