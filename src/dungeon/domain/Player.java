package dungeon.domain;

import dungeon.utility.Direction;

public class Player implements Movable {

    private int horizontalPosition;
    private int verticalPosition;
    
    public Player() {
        this.horizontalPosition = 0;
        this.verticalPosition = 0;
    }

    @Override
    public int getHorizontalPosition() {
        return this.horizontalPosition;
    }

    @Override
    public int getVerticalPosition() {
        return this.verticalPosition;
    }

    @Override
    public void move(Direction dir) {
        switch (dir) {
            case UP:
                this.verticalPosition -= 1;
                break;
            case DOWN:
                this.verticalPosition += 1;
                break;
            case LEFT:
                this.horizontalPosition -= 1;
                break;
            case RIGHT:
                this.horizontalPosition += 1;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "@ " + this.horizontalPosition + " " + this.verticalPosition;
    }
}
