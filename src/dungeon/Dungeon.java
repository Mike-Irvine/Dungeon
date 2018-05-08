package dungeon;

import dungeon.domain.DungeonMap;
import dungeon.utility.Direction;
import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class Dungeon {
    
    private int moves;
    private final boolean vampiresMove;
    private final DungeonMap dungeonMap;
    private Scanner reader;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        this.dungeonMap = new DungeonMap(length, height);
        this.dungeonMap.addVampires(vampires);
        this.reader = new Scanner(System.in);
    }
    
    public void run() {
        
        String command = "";
        

        
        while (true) {
            if (this.dungeonMap.getVampires().isEmpty()) { // all vampires dead, you win!
                System.out.println("YOU WIN");
                break;
            }
            if (this.moves <= 0) {
                System.out.println("YOU LOSE");
                break;
            }
            this.gameState();
            this.handleCommand(this.reader.nextLine());
            
        }
    }
    
    public void gameState() {
            // print game state
            System.out.println(moves);
            System.out.println();
            System.out.println(this.dungeonMap.movablesToString());
            System.out.println(this.dungeonMap.mapToString());
    }
    
    public void handleCommand(String command) {
//        Pattern p = Pattern.compile("[wasd]");
//        Matcher m = p.matcher(command);
        char[] commandCharacters;
        
        // check for invalid commands, (any character outside of w, a, s or d. Ignore input and return to control loop
        if (!command.matches("[wasd]*")) {
            return;
        }
        commandCharacters = command.toCharArray(); // split user input into individual direction commands
        
        for (char character : commandCharacters) {
            this.move(this.parseDirection(character));
        }
        
        this.moves--;
    }
    
    public Direction parseDirection(char character) {
        switch (character) {
            case 'w':
                return Direction.UP;
            case 's':
                return Direction.DOWN;
            case 'a':
                return Direction.LEFT;
            case 'd':
                return Direction.RIGHT;
            default:
               // will NOT execute due to RegEx in handleCommand()
                return Direction.UP;
        }
    }
    
    public void move(Direction dir) {
        this.dungeonMap.stepPlayer(dir);
        if (this.vampiresMove) {
            this.dungeonMap.stepVampires();
        }
    }
}
