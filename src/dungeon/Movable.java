package dungeon;

public interface Movable {
//    String position();
    int horizontalPosition();
    int verticalPosition();
    void move(String direction);
}
