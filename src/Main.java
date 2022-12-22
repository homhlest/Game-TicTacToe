
import java.util.Random;
import java.util.Scanner;

/**
 * This program is a cut down version of the Tictactoe game.
 * There is no selection of level difficulty.
 * The player always starts first.
 */

public class Main {

    public static final String START = "s";
    public static final String EXIT = "e";
    public static int counterXandO; // Amount of X and O on the field in order to define the draw.
    public static int moveCounter; // Amount of moves to avoid more than one move by one player.

    public static void main(String[] args) {

        while (true) {
            System.out.println("Start: s | Exit: e ");

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            resetCounters();
            startToPlay(command);

            if (command.equals(EXIT)) { // Exit from game.
                System.out.println("Game over.");
                break;
            }
        }
    }

    /**
     * This method is responsible for the game as a whole.
     *
     * @param command user input for start game or exit from game.
     */
    private static void startToPlay(String command) {
        if (START.equals(command)) {
            GameField gameFieldArray = new GameField(3, 3);
            gameFieldArray.outputGameField();
            game(gameFieldArray); // Game.
        } else if ((!EXIT.equals(command))) {
            System.out.println("Wrong input.\n");
        }
    }

    /**
     * This method resets counters to 0 for new game.
     */
    private static void resetCounters() {
        counterXandO = 0;
        moveCounter = 0;
    }

    /**
     * This method is responsible for the logic of the game as a whole.
     *
     * @param gameField two-dimensional array.
     */
    private static void game(GameField gameField) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter coordinates:");

            int c1, c2; // For transforming input string into int.

            String c1Input = scanner.next();
            // Check if first input is a digit (ASCII) and is in a range from 1 to 3.
            if (isNumber(c1Input) && isInRange(Integer.parseInt((c1Input)))) c1 = Integer.parseInt((c1Input)) - 1;
            else {
                continue;
            }

            String c2Input = scanner.next();
            // Check if second input is a digit (ASCII) and is in a range from 1 to 3.
            if (isNumber(c2Input) && isInRange(Integer.parseInt((c2Input)))) c2 = Integer.parseInt((c2Input));
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
            System.out.println(counterXandO);

            if (isResult(gameField)) break; // Result
        }
    }

    /**
     * This method is responsible for the opponent's logic in the game.
     *
     * @param gameField two-dimensional array.
     */
    private static void moveO(GameField gameField) {
        /*Opponent logic.*/
        System.out.println();
        System.out.println("Opponent's move:");

        int c1, c2;

        if (moveCounter == 0) checkRowsAndMove(gameField, "O");
        if (moveCounter == 0) checkColsAndMove(gameField, "O");
        if (moveCounter == 0) checkDiagonals(gameField, "O");

        if (moveCounter == 0) checkRowsAndMove(gameField, "X");
        if (moveCounter == 0) checkColsAndMove(gameField, "X");
        if (moveCounter == 0) checkDiagonals(gameField, "X");

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

    /**
     * This method checks user input.
     *
     * @param c input of coordinate.
     * @return is input in the range (1,3) or not.
     */
    private static boolean isInRange(int c) {
        if (c > 3) { // Check if input is in range from 1 to 3.
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        return true;
    }

    /**
     * This method checks if the input is a number.
     *
     * @param coor1Input input of coordinate.
     * @return is number.
     */
    private static boolean isNumber(String coor1Input) {
        for (int i = 0; i < coor1Input.length(); i++) {
            if ((coor1Input.charAt(i) < 48) || (coor1Input.charAt(i) > 57)) {
                System.out.println("You should enter two numbers.");
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if is the winner (three X/O in row, three X/O in col, three X/O in diagonals).
     *
     * @param gameField two-dimensional array.
     * @return if is winner.
     */
    private static boolean isWinner(GameField gameField, String xOrO) {
        int counter = 0;
        // Rows.
        for (int row = 0; row < gameField.getLengthRow(); row++) {
            for (int col = 0; col < gameField.getLengthCol(); col++) {
                if (gameField.isCellWithXorO(row, col, xOrO)) {
                    counter++;
                    if (counter == 3) return true;
                }
            }
            counter = 0;
        }

        // Cols.
        for (int col = 0; col <= 3; col++) {
            for (int row = 0; row < gameField.getLengthRow(); row++) {
                if (gameField.isCellWithXorO(row, col, xOrO)) {
                    counter++;
                    if (counter == 3) return true;
                }
            }
            counter = 0;
        }

        // Diagonals.
        return ((gameField.isCellWithXorO(0, 1, xOrO)
                && gameField.isCellWithXorO(1, 2, xOrO)
                && gameField.isCellWithXorO(2, 3, xOrO))
                || (gameField.isCellWithXorO(2, 1, xOrO)
                && gameField.isCellWithXorO(1, 2, xOrO)
                && gameField.isCellWithXorO(0, 3, xOrO)));
    }

    /**
     * This method outputs results.
     *
     * @param gameField two-dimensional array.
     * @return if there is result of the game.
     */
    private static boolean isResult(GameField gameField) {

        /*Condition for draw.*/
        if (isWinner(gameField, "X") && isWinner(gameField, "O") || isDraw()) {
            System.out.println("It's a draw.\n");
            return true;
        }

        /*Condition for X win.*/
        if (isWinner(gameField, "X")) {
            System.out.println("You win!\n");
            return true;
        }
        /*Condition for O win.*/
        if (isWinner(gameField, "O")) {
            System.out.println("O wins.\n");
            return true;
        }
        return false;
    }

    /**
     * This method checks if is it draw.
     *
     * @return draw.
     */
    private static boolean isDraw() {
        return (counterXandO == 9);
    }

    /**
     * This method checks all rows. If there is two similar elements in one row,
     * third cell will be occupied (if it is empty).
     *
     * @param gameField two-dimensional array.
     */
    private static void checkRowsAndMove(GameField gameField, String xOrO) {
        int counter = 0;
        for (int row = 0; row < gameField.getLengthRow(); row++) {
            for (int col = 0; col < gameField.getLengthCol(); col++) {
                if (gameField.isCellWithXorO(row, col, xOrO)) {
                    counter++;

                    if (counter == 2) {

                        for (int colTemp = 0; colTemp < gameField.getLengthCol(); colTemp++) {

                            if (gameField.isNotOccupied(row,colTemp)) {
                                gameField.setO(row,colTemp);
                                counterXandO++;
                                moveCounter++;
                                break;
                            }
                        }
                    }
                }
            }
            counter = 0;
        }
    }

    /**
     * This method checks all cols. If there is two similar elements in one diagonal,
     * third cell will be occupied (if it is empty).
     *
     * @param gameField two-dimensional array.
     */
    private static void checkColsAndMove(GameField gameField, String xOrO) {
        int counter = 0;

        for (int col = 0; col < gameField.getLengthCol(); col++) {
            for (int row = 0; row < gameField.getLengthRow(); row++) {

                if (gameField.isCellWithXorO(row, col, xOrO)) {
                    counter++;

                    if (counter == 2) {

                        for (int rowTemp = 0; rowTemp < gameField.getLengthRow(); rowTemp++) {

                            if (gameField.isNotOccupied(rowTemp,col)) {
                                gameField.setO(rowTemp,col);
                                counterXandO++;
                                moveCounter++;
                                break;
                            }
                        }
                    }
                }
            }
            counter = 0;
        }
    }

    /**
     * This method checks two diagonals. If there is two X/O in the diagonal,
     * O/X occupied third cell (if it is empty).
     *
     * @param gameField two-dimensional array.
     */
    private static void checkDiagonals(GameField gameField, String xOrO) {

        if (gameField.isNotOccupied(0,1) && gameField.isCellWithXorO(1,2,xOrO) && gameField.isCellWithXorO(2,3,xOrO)) {
            gameField.setO(0,1);
            counterXandO++;
            moveCounter++;
            return;
        }

        if (gameField.isCellWithXorO(0,1,xOrO) && gameField.isNotOccupied(1,2) && gameField.isCellWithXorO(2,3,xOrO)) {
            gameField.setO(1,2);
            counterXandO++;
            moveCounter++;
            return;
        }

        if (gameField.isCellWithXorO(0,1,xOrO) && gameField.isCellWithXorO(1,2,xOrO) && gameField.isNotOccupied(2,3)) {
            gameField.setO(2,3);
            counterXandO++;
            moveCounter++;
            return;
        }

        if (gameField.isNotOccupied(2,1) && gameField.isCellWithXorO(1,2,xOrO) && gameField.isCellWithXorO(0,3,xOrO)) {
            gameField.setO(2,1);
            counterXandO++;
            moveCounter++;
            return;
        }

        if (gameField.isCellWithXorO(2,1,xOrO) && gameField.isNotOccupied(1,2) && gameField.isCellWithXorO(0,3,xOrO)) {
            gameField.setO(1,2);
            counterXandO++;
            moveCounter++;
            return;
        }

        if (gameField.isCellWithXorO(2,1,xOrO) && gameField.isCellWithXorO(1,2,xOrO) && gameField.isNotOccupied(0,3)) {
            gameField.setO(0,3);
            counterXandO++;
            moveCounter++;
        }

    }
}





