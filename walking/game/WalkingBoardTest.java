package walking.game;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;
import walking.game.util.Direction;

public class WalkingBoardTest {
    @Test
    public void testSimpleInit() {
        WalkingBoard board = new WalkingBoard(3);
        assertNotNull(board);
        assertEquals(3, board.getTile(0, 0));
        assertEquals(3, board.getTile(2, 2));
        assertEquals(3, board.getTile(1, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            board.getTile(3, 4);
        });
        int[] position = board.getPosition();
        assertEquals(0, position[0]);
        assertEquals(0, position[1]);
    }
    
    @Test
    public void testCustomInit() {
        int[][] customTiles = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        WalkingBoard board1 = new WalkingBoard(customTiles);
        assertNotNull(board1);
        assertEquals(1, board1.getTile(0, 0));
        assertEquals(5, board1.getTile(1, 1));
        assertEquals(8, board1.getTile(2, 1));
        assertThrows(IllegalArgumentException.class, () -> {
            board1.getTile(3, 4);
        });
    }
    
    @Test
    public void testMoves(){
        WalkingBoard board = new WalkingBoard(3);
        assertEquals(3, board.moveAndSet(Direction.RIGHT, 5)); 
        assertEquals(5, board.getTile(1, 0));

        assertEquals(3, board.moveAndSet(Direction.DOWN, 7));
        assertEquals(7, board.getTile(1, 1));

        assertEquals(3, board.moveAndSet(Direction.LEFT, 9));
        assertEquals(9, board.getTile(0, 1));

        assertEquals(3, board.moveAndSet(Direction.UP, 11));
        assertEquals(11, board.getTile(0, 0));

        assertEquals(0, board.moveAndSet(Direction.UP, 15));
        assertEquals(11, board.getTile(0, 0)); 
    }
}