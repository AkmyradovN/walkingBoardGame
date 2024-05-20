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
import walking.game.player.MadlyRotatingBuccaneer;


public class WalkingBoardWithPlayersTest {

    @Test
    public void testConstructorWithBoard() {
        int[][] board = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        WalkingBoardWithPlayers boardWithPlayers = new WalkingBoardWithPlayers(board, 2);

        assertNotNull(boardWithPlayers);
        assertEquals(2, boardWithPlayers.getPlayerCount());
        assertEquals(2, boardWithPlayers.getPlayerID(1));
        assertEquals(0, boardWithPlayers.getRound());
    }

    @Test
    public void testConstructorWithSize() {
        WalkingBoardWithPlayers boardWithPlayers = new WalkingBoardWithPlayers(3, 3);

        assertNotNull(boardWithPlayers);
        assertEquals(3, boardWithPlayers.getPlayerCount());
        assertEquals(1, boardWithPlayers.getPlayerID(0));
        assertEquals(0, boardWithPlayers.getRound());
    }

    @Test
    public void testWalk() {
        WalkingBoardWithPlayers boardWithPlayers = new WalkingBoardWithPlayers(3, 2);

        int[] player1Steps = boardWithPlayers.walk(5);
        assertEquals(2, player1Steps.length);
        for (int step : player1Steps) {
            assertEquals(0, step);
        }

        int[] player2Steps = boardWithPlayers.walk(3);
        assertEquals(2, player2Steps.length);
        for (int step : player2Steps) {
            assertEquals(0, step);
        } 
    } 

}
