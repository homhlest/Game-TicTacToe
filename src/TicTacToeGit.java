import java.util.Random;
import java.util.Scanner;

/**
 * This program is a cut down version of the Tictactoe game.
 * There is no selection of level difficulty.
 * The player always starts first.
 */

public class TicTacToeGit {

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
            String[][] gameFieldArray = new String[3][5]; // Left and right borders take two cols.
            outputStartField(gameFieldArray);
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
    private static void game(String[][] gameField) {

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

            if (isCellOccupied(c1, c2, gameField)) continue; // Check if cell is occupied.

            moveX(c1, c2, gameField);
            if (counterXandO < 9) moveO(gameField); // Number 9 means that all cells are occupied.
            if (isResult(gameField)) break;
        }
    }

    /**
     * This method check cell and if the cell is empty X makes move here.
     *
     * @param c1        row coordinate of the cell.
     * @param c2        col coordinate of the cell.
     * @param gameField two-dimensional array.
     */
    private static void moveX(int c1, int c2, String[][] gameField) {
        if (gameField[c1][c2].equals(" ")) {
            gameField[c1][c2] = "X";
            counterXandO++;
        }
        outputCurrentField(gameField);
    }

    /**
     * This method is responsible for the opponent's logic in the game.
     *
     * @param gameField two-dimensional array.
     */
    private static void moveO(String[][] gameField) {
        /*Opponent logic.*/
        System.out.println();
        System.out.println("Opponent's move:");

        int c1, c2;

        if (moveCounter == 0) checkFirstMove(gameField, "X");

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
                if (c1 < gameField.length && c2 < gameField[0].length && gameField[c1][c2].equals(" ")) {
                    gameField[c1][c2] = "O";
                    counterXandO++;
                    break;
                }
            }
        }
        outputCurrentField(gameField);
        moveCounter = 0;
    }

    /**
     * This method checks central cell on the beginning of the game if first player
     * occupied one of the corner cells.
     *
     * @param gameField two-dimensional array.
     * @param o X or O.
     */
    private static void checkFirstMove(String[][] gameField, String o) {
        if (counterXandO == 1 && gameField[1][2].equals(" ") && (gameField[0][1].equals(o)
                || gameField[0][3].equals(o) || gameField[2][1].equals(o) || gameField[2][3].equals(o))) {
            gameField[1][2] = "O";
            counterXandO++;
            moveCounter++;
        }
    }

    /**
     * This method checks all rows. If there is two similar elements in one row,
     * third cell will be occupied (if it is empty).
     *
     * @param gameField two-dimensional array.
     */
    private static void checkRowsAndMove(String[][] gameField, String check) {
        int counter = 0;
        for (int row = 0; row < gameField.length; row++) {
            for (int col = 0; col < gameField[0].length; col++) {
                if (gameField[row][col].equals(check)) {
                    counter++;

                    if (counter == 2) {

                        for (int colTemp = 0; colTemp < gameField[0].length; colTemp++) {

                            if (gameField[row][colTemp].equals(" ")) {
                                gameField[row][colTemp] = "O";
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
    private static void checkColsAndMove(String[][] gameField, String check) {
        int counter = 0;

        for (int col = 0; col < gameField[0].length; col++) {
            for (int row = 0; row < gameField.length; row++) {

                if (gameField[row][col].equals(check)) {
                    counter++;

                    if (counter == 2) {

                        for (int rowTemp = 0; rowTemp < gameField.length; rowTemp++) {

                            if (gameField[rowTemp][col].equals(" ")) {
                                gameField[rowTemp][col] = "O";
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
     * This method checks if the move is possible by checking if the cell
     * is occupied or not.
     *
     * @param c1        input of first coordinate.
     * @param c2        input of second coordinate.
     * @param gameField two-dimensional array.
     * @return is cell occupied or not.
     */
    private static boolean isCellOccupied(int c1, int c2, String[][] gameField) {
        if (!gameField[c1][c2].equals(" ")) {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
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
     * This method outputs results.
     *
     * @param gameField two-dimensional array.
     * @return if there is result of the game.
     */
    private static boolean isResult(String[][] gameField) {

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
     * This method outputs start game field on the screen.
     *
     * @param gameField two-dimensional array.
     */
    private static void outputStartField(String[][] gameField) {
        System.out.println("=========");
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                /* Makes left and right borders of the game field.*/
                if (j == 0 || j == 4) {
                    gameField[i][j] = "|";
                } else {
                    gameField[i][j] = " ";
                }
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=========");
    }

    /**
     * This method outputs current state of the game field on the screen.
     *
     * @param gameField two-dimensional array.
     */
    private static void outputCurrentField(String[][] gameField) {
        System.out.println("=========");
        for (String[] strings : gameField) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
        System.out.println("=========");
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
     * This method checks if is the winner (three X/O in row, three X/O in col, three X/O in diagonals).
     *
     * @param gameField two-dimensional array.
     * @return if is winner.
     */
    private static boolean isWinner(String[][] gameField, String xO) {
        int counter = 0;
        // Rows.
        for (int row = 0; row < gameField.length; row++) {
            for (int col = 0; col < gameField[0].length; col++) {
                if (gameField[row][col].equals(xO)) {
                    counter++;
                    if (counter == 3) return true;
                }
            }
            counter = 0;
        }

        // Cols.
        for (int col = 0; col <= 3; col++) {
            for (int row = 0; row < gameField.length; row++) {
                if (gameField[row][col].equals(xO)) {
                    counter++;
                    if (counter == 3) return true;
                }
            }
            counter = 0;
        }

        // Diagonals.
        return (gameField[0][1].equals(xO) && gameField[1][2].equals(xO) && gameField[2][3].equals(xO))
                || (gameField[2][1].equals(xO) && gameField[1][2].equals(xO) && gameField[0][3].equals(xO));
    }

//    /**
//     * This method checks two diagonals. If there is two X/O in the diagonal,
//     * O/X occupied third cell (if it is empty).
//     *
//     * @param gameField two-dimensional array.
//     */
//    private static void checkDiagonals(String[][] gameField, String check) {
//
//        if (gameField[0][1].equals(" ") && gameField[1][2].equals(check) && gameField[2][3].equals(check)) {
//            gameField[0][1] = "O";
//            counterXandO++;
//            moveCounter++;
//            return;
//        }
//
//        if (gameField[0][1].equals(check) && gameField[1][2].equals(" ") && gameField[2][3].equals(check)) {
//            gameField[1][2] = "O";
//            counterXandO++;
//            moveCounter++;
//            return;
//        }
//
//        if (gameField[0][1].equals(check) && gameField[1][2].equals(check) && gameField[2][3].equals(" ")) {
//            gameField[2][3] = "O";
//            counterXandO++;
//            moveCounter++;
//            return;
//        }
//
//        if (gameField[2][1].equals(" ") && gameField[1][2].equals(check) && gameField[0][3].equals(check)) {
//            gameField[2][1] = "O";
//            counterXandO++;
//            moveCounter++;
//            return;
//        }
//
//        if (gameField[2][1].equals(check) && gameField[1][2].equals(" ") && gameField[0][3].equals(check)) {
//            gameField[1][2] = "O";
//            counterXandO++;
//            moveCounter++;
//            return;
//        }
//
//        if (gameField[2][1].equals(check) && gameField[1][2].equals(check) && gameField[0][3].equals(" ")) {
//            gameField[0][3] = "O";
//            counterXandO++;
//            moveCounter++;
//        }
//    }
}