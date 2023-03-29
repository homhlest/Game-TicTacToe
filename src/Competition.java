
import java.util.Scanner;

/**
 * This class is responsible for the start of the new competition.
 */

public class Competition {
    public static final String START = "s";
    public static final String EXIT = "e";

    public static void start() {
        while (true) {
            System.out.println("Start: s | Exit: e ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            Game.resetCounters();
            startGame(command);
            if (command.equals(EXIT)) { // Exit from game.
                System.out.println("Game over.");
                break;
            }
        }
    }

    /**
     * This method starts each separate game in the competition.
     *
     * @param command user input for start game or exit from game.
     */
    private static void startGame(String command) {
        if (START.equals(command)) {
            Field gameFieldArray = new Field(3, 3);
            gameFieldArray.outputGameField();
            Game.start(gameFieldArray); // Game.
        } else if ((!EXIT.equals(command))) {
            System.out.println("Wrong input.\n");
        }
    }
}