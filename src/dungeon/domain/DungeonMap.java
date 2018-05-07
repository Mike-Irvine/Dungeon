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

    public ArrayList<Movable> getVampires() {
        return this.vampires;
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
                    break; // will NOT execute due to range of possible random ints
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
            default: // will NOT execute due to Direction enum
        }
        
        ArrayList<Movable> deadVampires = new ArrayList<>(); // create list to store killed vampires
        
        if (movable.equals(this.player)) { // handle player movements, and possible collisions with vampires
            for (int i = 0; i < this.vampires.size(); i++) {
                if (collideWithMovable(dir, this.player, this.vampires.get(i))) {
                    deadVampires.add(this.vampires.get(i)); // in case of collision, vampire is killed
                }
            }
            this.vampires.removeAll(deadVampires); // remove killed vampires from list of vampires
        } else { // handle vampire movements
            if (collideWithMovable(dir, movable, this.player)) { // handle moving into player's space
                deadVampires.add(movable);
                return; // movable has died, movement is not completed
            }
            for (int j = 0; j < this.vampires.size(); j++) {
                if (collideWithMovable(dir, movable, this.vampires.get(j))) { // self-collision can't occur so no logical issue with comparing against full list of vampires
                    return; // moving vampire collides with other vampire, movement skipped
                }
            }
        }
        movable.move(dir); // if switch case didn't exit method, tell movable to move
    }
    
    public boolean collideWithMovable(Direction dir, Movable movingMovable, Movable stationaryMovable) {
        switch (dir) {
            case UP:
                if (this.horizontalMatches(movingMovable, stationaryMovable)) { // both movables in same column
                    if (movingMovable.getVerticalPosition() == (stationaryMovable.getVerticalPosition() - 1)) { // movingMovable currently one step below stationaryMovable
                        return true; // moving causes a collision
                    }
                }
                return false;
            case DOWN:
                if (this.horizontalMatches(movingMovable, stationaryMovable)) { // both movables in same column
                    if (movingMovable.getVerticalPosition() == (stationaryMovable.getVerticalPosition() + 1)) { // movingMovable currently one step above stationaryMovable
                        return true; // moving causes a collision
                    }
                }
                return false;
            case LEFT:
                if (this.verticalMatches(movingMovable, stationaryMovable)) { // both movables in same column
                    if (movingMovable.getHorizontalPosition() == (stationaryMovable.getHorizontalPosition() - 1)) { // movingMovable currently one step right of stationaryMovable
                        return true; // moving causes a collision
                    }
                }
                return false;
            case RIGHT:
                if (this.verticalMatches(movingMovable, stationaryMovable)) { // both movables in same column
                    if (movingMovable.getHorizontalPosition() == (stationaryMovable.getHorizontalPosition() + 1)) { // movingMovable currently one step left of stationaryMovable
                        return true; // moving causes a collision
                    }
                }
                return false;
            default: // will NOT execute due to Direction enum
                return false;
        }
    }
    
    public boolean horizontalMatches(Movable movableOne, Movable movableTwo) {
        if (movableOne.getHorizontalPosition() == movableTwo.getHorizontalPosition()) {
            return true;
        }
        return false;
    }
    
    public boolean verticalMatches(Movable movableOne, Movable movableTwo) {
        if (movableOne.getVerticalPosition() == movableTwo.getVerticalPosition()) {
            return true;
        }
        return false;
    }
    
    public String movablesToString() {
        String string = this.player.toString() + "\n";
        for (Movable vampire : vampires) {
            string += vampire.toString() + "\n";
        }
        return string;
    }
    
    public String mapToString() {
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
