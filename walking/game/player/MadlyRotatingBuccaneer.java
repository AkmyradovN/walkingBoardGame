package walking.game.player;

import walking.game.player.Player;

public class MadlyRotatingBuccaneer extends Player {
    private int turnCount;
    public MadlyRotatingBuccaneer() {
         super();
         this.turnCount = 0;
    }
    @Override
    public void turn() {
        if (turnCount == 0) {
            turnCount++;
        } else if (turnCount % 2 == 1) {
            direction = direction.next();
            turnCount++;
        } else {
            for (int i = 0; i < 2; i++) {
                direction = direction.next();
            }
            turnCount++;
        }
    }
}
