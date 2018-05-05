package dungeon;

public interface Movable {
    int horizontalPosition();
    int verticalPosition();
    void move(Direction dir);
}
