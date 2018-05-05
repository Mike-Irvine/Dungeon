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
    public void move(String direction) {
        switch (direction) {
            case "w":
                this.verticalPosition -= 1;
                break;
            case "s":
                this.verticalPosition += 1;
                break;
            case "a":
                this.horizontalPosition -= 1;
                break;
            case "d":
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
