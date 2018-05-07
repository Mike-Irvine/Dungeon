package dungeon;

import dungeon.logic.Dungeon;

public class Main {
    public static void main(String[] args) {
        new Dungeon(5,5,3,10,false).run();
    }
}
