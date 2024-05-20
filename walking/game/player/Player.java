package walking.game.player;

import walking.game.util.Direction;

public class Player {
    private int score;
    protected Direction direction;
    public Player() {
        this.score = 0;
        this.direction = Direction.UP;
    }

    public void increaseScore(int points) {
        score += points;
    }
    public int getScore() {
        return score;
    }
    public Direction getDirection() {
        return direction;
    }

    public void addToScore(int additionalScore) {
        score += additionalScore;
    }

    public void turn() {
        direction = direction.next();
    }
}
