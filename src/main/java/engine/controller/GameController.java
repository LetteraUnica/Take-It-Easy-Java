package engine.controller;


import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import engine.state.MatchState;
import exceptions.FatalGameErrorException;

import java.util.List;

public class GameController implements GameInterface {

    private MatchState matchState;

    public GameController() throws FatalGameErrorException {
        matchState = new MatchState();
    }

    @Override
    public TileInterface getCurrentTile() {
        return matchState.getCurrentTile();
    }

    @Override
    public int getCurrentPlayer() {
        return matchState.getCurrentPlayer();
    }

    @Override
    public void nextPlayer() {
        matchState.nextPlayer();
    }

    @Override
    public void nextTurn() {
        matchState.drawTile();
    }

    @Override
    public void addPlayer(String newPlayerName) {
        BoardInterface newBoard = new BoardClassic(newPlayerName);
        matchState.addBoard(newBoard);
    }

    @Override
    public void removePlayer(int playerIndex) {
        matchState.deleteBoard(playerIndex);
    }

    @Override
    public List<Integer> getScores() { return matchState.getAllBoardsScore(); }

    @Override
    public boolean isGameOver() {
        return matchState.getCacheSize() == 0;
    }

    @Override
    public List<String> getNicknames() { return matchState.getBoardsNicknames(); }

    @Override
    public List<BoardInterface> getPlayers() { return matchState.getBoards(); }

    @Override
    public BoardInterface getSinglePlayer(int playerIndex) { return matchState.getBoardOfPlayer(playerIndex); }

}
