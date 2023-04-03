import java.util.Scanner;

/**
 * This class is responsible for the START
 * of the new game and EXIT from the game.
 */
public class Game {
    public static final String START = "s";
    public static final String EXIT = "e";

    public static void start() throws InterruptedException {
        while (true) {
            System.out.println("Start: s | Exit: e ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            Party.resetCounters();
            startParty(command);
            if (command.equals(EXIT)) { // Exit from game.
                System.out.println("Game over.");
                break;
            }
        }
    }

    /**
     * This method starts each separate party in the game.
     *
     * @param command user input for start game or exit from game.
     */
    private static void startParty(String command) throws InterruptedException {
        if (START.equals(command)) {
            Thread cd = new Thread(new CountDown());
            cd.start();
            cd.join();
            Field gameFieldArray = new Field(3, 3);
            gameFieldArray.outputGameField();
            Party.start(gameFieldArray); // Game.
        } else if ((!EXIT.equals(command))) {
            System.out.println("Wrong input.\n");
        }
    }
}
