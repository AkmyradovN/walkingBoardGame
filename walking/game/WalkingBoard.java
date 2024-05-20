package walking.game;

import walking.game.util.Direction;
import java.lang.IllegalArgumentException;


public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    private int size;
    public static final int BASE_TILE_SCORE = 3;
    public WalkingBoard(int size) {
        this.size = size;
        this.tiles = new int[size][size];
        initializeBoard(BASE_TILE_SCORE);
        this.x = 0;
        this.y = 0; 
    }
    public WalkingBoard(int[][] tiles) {
        this.tiles = copyTiles(tiles);
        this.size = tiles.length;
    }
    private void initializeBoard(int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = Math.max(value, BASE_TILE_SCORE);
            }
        }
    }
    public int[] getPosition() {
        return new int[]{x, y};
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private int[][] copyTiles(int[][] tiles) {
        int[][] copy = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            copy[i] = new int[tiles[i].length];
            System.arraycopy(tiles[i], 0, copy[i], 0, tiles[i].length);
        }
        return copy;
    }

    public int getTile(int x, int y) {
        if (!isValidPosition(x, y)) {
            throw new IllegalArgumentException("Invalid position");
        }
        return tiles[x][y];
    }
    
    public int[][] getTiles(){
        int[][] copy = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            copy[i] = new int[tiles[i].length];
            System.arraycopy(tiles[i], 0, copy[i], 0, tiles[i].length);
        }
        return copy;
    }
    public static int getXStep(Direction direction) {
        if (direction == Direction.LEFT) return -1;
        if (direction == Direction.RIGHT) return 1;
        return 0;
    }
    public static int getYStep(Direction direction) {
        if (direction == Direction.UP) return -1;
        if (direction == Direction.DOWN) return 1;
        return 0;
    }
    public int moveAndSet(Direction direction, int value) {
        int newX = x + getXStep(direction);
        int newY = y + getYStep(direction);
        
        if (!isValidPosition(newX, newY)) {
            return 0;
        }
        int oldValue = tiles[newX][newY];
        tiles[newX][newY] = value;
        x = newX;
        y = newY;
        return oldValue;
    }
}