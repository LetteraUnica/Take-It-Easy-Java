package utils;

import engine.controller.GameControllerInterface;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import exceptions.PlayerNameNotFoundException;
import org.jetbrains.annotations.NotNull;

public class BoardFiller {

    public void fillAllBoardsHavingController(@NotNull GameControllerInterface controller, TileInterface tile, int startIndex) throws PlayerNameNotFoundException {
        for (String playerName: controller.getPlayersNicknames()) {
            BoardInterface testBoard = controller.getBoardOf(playerName);
            fillSingleBoard(testBoard, tile, startIndex);
        }
    }

    public void fillSingleBoard(BoardInterface board, TileInterface tile, int startIndex) {
        for (int i = startIndex; i < 19; ++i) {
            if (tile == null) {
                tile = new Tile(i, i, i, i);
            }
            board.placeTile(i, tile);
        }
    }

}
