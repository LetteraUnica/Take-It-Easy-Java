package engine.controller;


import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import engine.state.MatchState;
import exceptions.FatalGameErrorException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class GameController implements GameInterface {

    private final MatchState matchState;

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
        return getPlayers().stream().allMatch(BoardInterface::isBoardFull) && isLastPlayer();
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
    public boolean isCurrentPlayer(String playerName) {
        return matchState.getBoardsNicknames().get(matchState.getCurrentPlayer()).equals(playerName);
    }

    @Override
    public List<String> getGameWinners() {
        List<Integer> scores = matchState.getAllBoardsScore();
        int[] winnersIndices =  IntStream.range(0, scores.size()).filter(playerIndex -> Objects.equals(scores.get(playerIndex), scores.stream().max(Integer::compare).get())).toArray();
        return Arrays.stream(winnersIndices).mapToObj(matchState.getBoardsNicknames()::get).toList();
    }

    @Override
    public int getWinnersScore() {
        return Collections.max(matchState.getAllBoardsScore());
    }

    @Override
    public List<String> getNicknames() { return matchState.getBoardsNicknames(); }

    @Override
    public List<BoardInterface> getPlayers() { return matchState.getBoards(); }

}