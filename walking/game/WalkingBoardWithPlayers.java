package walking.game;

import walking.game.player.Player;
import walking.game.player.MadlyRotatingBuccaneer;
import walking.game.util.Direction;

public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int currPlayerIndex;
    private int round;
    public static final int SCORE_EACH_STEP = 13;

    public WalkingBoardWithPlayers(int[][] board, int playerCount) {
        super(board);
        initPlayers(playerCount);
        this.currPlayerIndex = 0;
        this.round = 0;
    }

    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
        initPlayers(playerCount);
        this.currPlayerIndex = 0;
        this.round = 0;
    }

    private void initPlayers(int playerCount) {
        if (playerCount < 2) {
            throw new IllegalArgumentException("playerCount must be >= 2");
        }
        players = new Player[playerCount];
        players[0] = new MadlyRotatingBuccaneer();
        for (int i = 1; i < playerCount; i++) {
            players[i] = new Player();
        }
    }

    public int getPlayerCount() {
        return players.length;
    }
    public int getPlayerID(int index) {
        if (index < 0 || index >= players.length) {
            throw new IllegalArgumentException("Invalid player index");
        }
        return index + 1;
    }
    public int getRound() {
        return round;
    }
    public int[] walk(int... stepCounts) {
        int[] playerScores = new int[players.length];
        for (int i = 0; i < stepCounts.length; i++) {
            int steps = stepCounts[i];
            Player currPlayer = players[i % players.length];
            int[] playerPosition = getPosition();
            Direction currDirection = currPlayer.getDirection();
            int totalSteps = Math.min(steps, SCORE_EACH_STEP);

            for (int s = 0; s < totalSteps; s++) {
                int nextX = playerPosition[0] + getXStep(currDirection);
                int nextY = playerPosition[1] + getYStep(currDirection);

                if (!isValidPosition(nextX, nextY)) {
                    break;
                }

                int tileValue = getTile(nextX, nextY);
                currPlayer.increaseScore(tileValue);
                moveAndSet(currDirection, currPlayer.getScore());

                playerPosition[0] = nextX;
                playerPosition[1] = nextY;
            }

            playerScores[i % players.length] = currPlayer.getScore();
            currPlayer.turn();
        }
        currPlayerIndex = (currPlayerIndex + 1) % players.length;
        round++;
        return playerScores;
    }
}
