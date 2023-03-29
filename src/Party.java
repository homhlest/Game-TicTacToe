
import java.util.Random;
import java.util.Scanner;

/**
 * This class is responsible for the logic of each party in the game.
 */
public class Party {
    public static int counterXandO; // Amount of X and O on the field in order to define the draw.
    public static int moveCounter; // Amount of moves to avoid more than one move by one player.

    /**
     * This method is responsible for the party as whole.
     *
     * @param gameField two-dimensional array.
     */
    public static void start(Field gameField) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter coordinates:");

            int c1, c2; // For transforming input string into int.

            String c1Input = scanner.next();
            // Check if first input is a digit (ASCII) and is in a range from 1 to 3.
            if (InputController.isNumber(c1Input) && InputController.isInRange(Integer.parseInt((c1Input))))
                c1 = Integer.parseInt((c1Input)) - 1;
            else {
                continue;
            }

            String c2Input = scanner.next();
            // Check if second input is a digit (ASCII) and is in a range from 1 to 3.
            if (InputController.isNumber(c2Input) && InputController.isInRange(Integer.parseInt((c2Input))))
                c2 = Integer.parseInt((c2Input));
            else {
                continue;
            }

            if (!gameField.isNotOccupied(c1, c2)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (gameField.isNotOccupied(c1, c2)) {
                gameField.setX(c1, c2); // X moves
                counterXandO++;
            }

            if (counterXandO < 9) moveO(gameField); // O moves

            if (ResultController.isResult(gameField)) break; // Result
        }
    }

    /**
     * This method resets counters to 0 for new game.
     */
    public static void resetCounters() {
        counterXandO = 0;
        moveCounter = 0;
    }

    /**
     * This method is responsible for the opponent's logic in the game.
     *
     * @param gameField two-dimensional array.
     */
    private static void moveO(Field gameField) {
        /*Opponent logic.*/
        System.out.println();
        System.out.println("Opponent's move:");

        int c1, c2;

        if (moveCounter == 0) MoveController.checkRowsAndMove(gameField, "O");
        if (moveCounter == 0) MoveController.checkColsAndMove(gameField, "O");
        if (moveCounter == 0) MoveController.checkDiagonals(gameField, "O");

        if (moveCounter == 0) MoveController.checkRowsAndMove(gameField, "X");
        if (moveCounter == 0) MoveController.checkColsAndMove(gameField, "X");
        if (moveCounter == 0) MoveController.checkDiagonals(gameField, "X");

        if (moveCounter == 0) {
            while (true) {
                Random random = new Random(); // Random move.
                c1 = random.nextInt(5);
                c2 = random.nextInt(5);
                if (c1 < gameField.getLengthRow() && c2 < gameField.getLengthCol() && gameField.isNotOccupied(c1, c2)) {
                    gameField.setO(c1, c2);
                    counterXandO++;
                    break;
                }
            }
        }
        moveCounter = 0;
    }
}
