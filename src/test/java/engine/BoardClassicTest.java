package engine;

import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import engine.model.tile.Tile;
import engine.model.tile.TileInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class BoardClassicTest {
    @Test
    void testBoard(){
        ArrayList<Object> testInitializeBoard = new ArrayList<>(Collections.nCopies(19, null));
        BoardInterface testBoard = new BoardClassic("testName");
        assertEquals("testName", testBoard.getNickname());
        assertEquals(testInitializeBoard, testBoard.getBoard());
    }



    @Test
    void testPlaceTile(){
        BoardInterface testBoard = new BoardClassic("testName");
        for (int i =0; i<19;++i){
            TileInterface testStandardTile = new Tile(i,i,i,i);
            testBoard.placeTile(i, testStandardTile);
        }
        assertEquals( 3, testBoard.getBoard().get(3).getIdTile());
        assertEquals( new ArrayList<>(Collections.nCopies(3, 3)), testBoard.getBoard().get(3).getValues());
    }

    @Test
    void testCopy(){
        BoardInterface testBoard = new BoardClassic("testName");
        for (int i =0; i<19;++i){
            TileInterface testStandardTile = new Tile(i,i,i,i);
            testBoard.placeTile(i, testStandardTile);
        }
        assertEquals("testName", testBoard.copy().getNickname());
        assertEquals(testBoard.getBoard(), testBoard.copy().getBoard());
    }

    @Test
    void getScore(){
        BoardInterface testBoard = new BoardClassic("testName");
        for (int i =0; i<19;++i){
            TileInterface testStandardTile = new Tile(i,1,1,1);
            testBoard.placeTile(i, testStandardTile);
        }
        assertEquals( 57, testBoard.getScore());
    }


    @Test
    void isBoardFull(){
        BoardInterface testBoard = new BoardClassic("testName");
        assertFalse(testBoard.isBoardFull());
        for (int i =0; i<19;++i){
            TileInterface testStandardTile = new Tile(i,1,1,1);
            testBoard.placeTile(i, testStandardTile);
        }
        assertTrue(testBoard.isBoardFull());
    }
}

