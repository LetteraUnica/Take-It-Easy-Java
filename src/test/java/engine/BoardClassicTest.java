package engine;

import engine.model.board.BoardClassic;
import engine.model.board.BoardInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardClassicTest {
    @Test
    void testBoard(){
        String testName = "testName";
        ArrayList<Object> testInitializeBoard = new ArrayList<>(Collections.nCopies(19, null));
        BoardInterface testBoard = new BoardClassic(testName);
        assertEquals("testName", testBoard.getNickname(),);
        assertEquals(testInitializeBoard, testBoard.getBoard());
    }


}

