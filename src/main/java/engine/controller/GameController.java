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
        matchState.drawTile();
    }

    @Override
    public TileInterface getCurrentTile() {
        return matchState.getCurrentTile();
    }

    @Override
    public String getCurrentPlayer() {
        return matchState.getBoardsNicknames().get(matchState.getCurrentPlayer());
    }

    @Override
    public void nextPlayer() { matchState.nextPlayer(); }

    @Override
    public void nextTurn() {
        if (isLastPlayer()) {
            matchState.drawTile();
        }
        matchState.nextPlayer();
    }

    private boolean isLastPlayer() {
        return matchState.getCurrentPlayer() == matchState.getBoards().size() - 1;
    }

    @Override
    public void addPlayer(String newPlayerName) {
        BoardInterface newBoard = new BoardClassic(newPlayerName);
        matchState.addBoard(newBoard);
    }

    @Override
    public void removePlayer(String playerName) {
        int playerIndex = getPlayerIndex(playerName);
        matchState.deleteBoard(playerIndex);
    }

    private int getPlayerIndex(String playerName) {
        return matchState.getBoardsNicknames().indexOf(playerName);
    }

    @Override
    public List<Integer> getScores() { return matchState.getAllBoardsScore(); }

    @Override
    public boolean isGameOver() {
        return (getPlayers().stream().allMatch(BoardInterface::isBoardFull)) && isLastPlayer();
    }

    @Override
    public BoardInterface getBoardOfPlayer(String playerName) {
        return matchState.getBoardOfPlayer(getPlayerIndex(playerName));
    }

    @Override
    public TileInterface getTileOfPlayer(String playerName, int tileId) {
        return matchState.getBoardOfPlayer(getPlayerIndex(playerName)).getTile(tileId);
    }

    @Override
    public void placeTile(int candidateTilePlacement) {
        matchState.getBoards().get(matchState.getCurrentPlayer()).placeTile(candidateTilePlacement, matchState.getCurrentTile());
    }

    @Override
    public List<String> getNicknames() { return matchState.getBoardsNicknames(); }

    @Override
    public List<BoardInterface> getPlayers() { return matchState.getBoards(); }

}