/**
 * This class checks if there is a result
 * in the game (draw or winner).
 */
public class ResultController {
    /**
     * This method checks if is the winner (three X/O in row, three X/O in col, three X/O in diagonals).
     *
     * @param gameField two-dimensional array.
     * @return if is winner.
     */
    public static boolean isWinner(Field gameField, String xOrO) {
        int counter = 0;
        // Rows.
        for (int row = 0; row < gameField.getLengthRow(); row++) {
            for (int col = 0; col < gameField.getLengthCol(); col++) {
                if (gameField.isOccupiedByXorO(row, col, xOrO)) {
                    counter++;
                    if (counter == 3) return true;
                }
            }
            counter = 0;
        }
        // Cols.
        for (int col = 0; col <= 3; col++) {
            for (int row = 0; row < gameField.getLengthRow(); row++) {
                if (gameField.isOccupiedByXorO(row, col, xOrO)) {
                    counter++;
                    if (counter == 3) return true;
                }
            }
            counter = 0;
        }
        // Diagonals.
        return ((gameField.isOccupiedByXorO(0, 1, xOrO)
                && gameField.isOccupiedByXorO(1, 2, xOrO)
                && gameField.isOccupiedByXorO(2, 3, xOrO))
                || (gameField.isOccupiedByXorO(2, 1, xOrO)
                && gameField.isOccupiedByXorO(1, 2, xOrO)
                && gameField.isOccupiedByXorO(0, 3, xOrO)));
    }

    /**
     * This method outputs results.
     *
     * @param gameField two-dimensional array.
     * @return if there is result of the game.
     */
    public static boolean isResult(Field gameField) {
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
    public static boolean isDraw() {
        return (Party.counterXandO == 9);
    }
}
