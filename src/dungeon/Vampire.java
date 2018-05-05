package dungeon;

public class Vampire implements Movable {

    private int horizontalPosition;
    private int verticalPosition;
    
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
        
    }

    @Override
    public String toString() {
        return "v " + this.horizontalPosition + " " + this.verticalPosition;
    }
    
    
    
}
