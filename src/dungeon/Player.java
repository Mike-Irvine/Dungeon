package dungeon;

public class Player implements Movable {

    private int horizontalPosition;
    private int verticalPosition;
    
    public Player() {
        this.horizontalPosition = 0;
        this.verticalPosition = 0;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "@ " + this.horizontalPosition + " " + this.verticalPosition;
    }
}
