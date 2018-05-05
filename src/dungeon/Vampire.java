package dungeon;

public class Vampire implements Movable {

    private int horizontalPosition;
    private int verticalPosition;

    public Vampire(int horizontalPosition, int verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }
    
    @Override
    public int horizontalPosition() {
        return this.horizontalPosition;
    }

    @Override
    public int verticalPosition() {
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
                this.horizontalPosition =+ 1;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "v " + this.horizontalPosition + " " + this.verticalPosition;
    }
}
