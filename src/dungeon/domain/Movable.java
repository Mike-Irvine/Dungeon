package dungeon.domain;

import dungeon.utility.Direction;

public interface Movable {
    int getHorizontalPosition();
    int getVerticalPosition();
    void move(Direction dir);
}
