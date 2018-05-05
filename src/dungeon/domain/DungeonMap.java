package dungeon.domain;

import dungeon.utility.Direction;
import java.util.ArrayList;
import java.util.Random;

public class DungeonMap {
    
    private final int length;
    private final int height;
    private final Movable player;
    private final ArrayList<Movable> vampires;

    public DungeonMap(int length, int height) {
        this.length = length;
        this.height = height;
        this.player = new Player();
        this.vampires = new ArrayList<>();
    }
    
    public void addVampires(int vampires) {
        for (int i = 0; i < vampires; i++) {
            // determine random starting location for each vampire
            int horizontalPosition = new Random().nextInt(length);
            int verticalPosition = new Random().nextInt(height);
            // add vampire to list of vampires
            this.vampires.add(new Vampire(horizontalPosition, verticalPosition));
        }
    }
    
    public void stepPlayer(Direction dir) {
        // attempt to move player in requested direction
        this.move(dir, this.player);
    }
    
    public void stepVampires() {
        // attempt to move each vampire
        for (Movable vampire : this.vampires) {
            switch (new Random().nextInt(4)) {
                case 0: // UP
                    this.move(Direction.UP, vampire);
                    break;
                case 1: // DOWN
                    this.move(Direction.DOWN, vampire);
                    break;
                case 2: // LEFT
                    this.move(Direction.LEFT, vampire);
                    break;
                case 3: // RIGHT
                    this.move(Direction.RIGHT, vampire);
                    break;
                default:
                    break;
            }
        }
    }
    
    // attempt to move movable in requested direction
    public void move(Direction dir, Movable movable) {
        switch (dir) {
            case UP:
                if (movable.getVerticalPosition() == 0) { // if already at top edge, don't move
                    return;
                }
                break;
            case DOWN:
                if (movable.getVerticalPosition() == this.height) { // if already at bottom edge, don't move
                    return;
                }
                break;
            case LEFT:
                if (movable.getHorizontalPosition() == 0) { // if already at left edge, don't move
                    return;
                }
                break;
            case RIGHT:
                if (movable.getHorizontalPosition() == this.length) { // if already at right edge, don't move
                    return;
                }
                break;
        }
        movable.move(dir); // if case didn't return, tell movable to move
    }
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                string.append(".");
            }
            if (this.player.getVerticalPosition() == i) {
                string.setCharAt(this.player.getHorizontalPosition(), '@');
            }
            for (Movable vampire : vampires) {
                if (vampire.getVerticalPosition() == i) {
                    string.setCharAt(vampire.getHorizontalPosition(), 'v');
                }
            }
            string.append("\n");
        }
        return string.toString();
    }
}
