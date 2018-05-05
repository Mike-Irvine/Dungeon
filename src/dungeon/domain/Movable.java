package dungeon.domain;

import dungeon.utility.Direction;

public interface Movable {
    int horizontalPosition();
    int verticalPosition();
    void move(Direction dir);
}
