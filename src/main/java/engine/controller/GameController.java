package engine.controller;


import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import engine.model.tile.TileInterface;
import engine.state.MatchState;
import exceptions.*;
import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class GameController implements GameControllerInterface {

    private final MatchState matchState;

    public GameController() throws TileCacheEmptyException {
        matchState = new MatchState();
        matchState.drawTile();
    }

    @Override
    public TileInterface getCurrentTile() {
        return matchState.getCurrentTile();
    }

    @Override
    public String getCurrentPlayerName() {
        return getPlayersNicknames().get(matchState.getCurrentPlayer());
    }

    @Override
    public void nextTurn() throws TileCacheEmptyException {
        if (isLastPlayer()) {
            matchState.drawTile();
        }
        matchState.setNextPlayer();
    }

    @Override
    public void addPlayer(String newPlayerName) {
        BoardInterface newBoard = new BoardClassic(newPlayerName);
        matchState.addBoard(newBoard);
    }

    @Override
    public void removePlayer(String playerName) throws NoBoardFoundException, PlayerNameNotFoundException {
        matchState.deleteBoard(matchState.getBoardIndex(playerName));
    }

    @Override
    public boolean isGameOver() {
        return getGameBoards().stream().allMatch(BoardInterface::isBoardFull) && isLastPlayer();
    }

    @Override
    public TileInterface getTileOf(String playerName, int tileId) throws PlayerNameNotFoundException {
        return getBoardOf(playerName).getTile(tileId);
    }

    @Override
    public void placeTileIn(int candidateTilePlacement) throws CellNotAvailableException {
        matchState.fillBoardCell(candidateTilePlacement);
    }

    @Override
    public List<Integer> getScores() {
        return getGameBoards().stream().map(BoardInterface::getScore).toList();
    }

    @Override
    public List<String> getGameWinners() {
        List<Integer> scores = getScores();
        int[] winnersIndices =  IntStream.range(0, scores.size()).filter(playerIndex -> Objects.equals(scores.get(playerIndex), scores.stream().max(Integer::compare).get())).toArray();
        return Arrays.stream(winnersIndices).mapToObj(getPlayersNicknames()::get).toList();
    }

    @Override
    public int getWinnersScore() {
        return Collections.max(getScores());
    }

    @Override
    public List<Point2D> getRepresentationOf(String playerName) throws PlayerNameNotFoundException, NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException {
        List<Point2D> hexagonCenterCoordinates = getBoardOf(playerName).getEuclideanCoordinates();
        if (hexagonCenterCoordinates.isEmpty() || hexagonCenterCoordinates.size() != getBoardOf(playerName).getBoard().size()) {
            throw new NumberOfTileCentersCoordinatesNotMatchingNumberOfBoardCellsException("Number of coordinates pairs for centering the tiles do not match number of board cells");
        }
        return hexagonCenterCoordinates;
    }

    @Override
    public List<String> getPlayersNicknames() { return getGameBoards().stream().map(BoardInterface::getNickname).toList(); }

    @Override
    public boolean isLastPlayer() {
        return matchState.getCurrentPlayer() == matchState.getNumberOfBoards() - 1;
    }

    private List<BoardInterface> getGameBoards() { return matchState.getBoards(); }

    private BoardInterface getBoardOf(String playerName) throws PlayerNameNotFoundException {
        return getGameBoards().get(matchState.getBoardIndex(playerName));
    }

}