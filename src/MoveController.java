/**
 * This class controls the moves by checking rows and cols.
 * If there is two similar elements in one row or col, third cell
 * will be occupied after move.
 */
public class MoveController {
    /**
     * This method checks all rows. If there is two similar elements in one row,
     * third cell will be occupied (if it is empty).
     *
     * @param gameField two-dimensional array.
     */
    public static void checkRowsAndMove(Field gameField, String xOrO) {
        int counter = 0;
        for (int row = 0; row < gameField.getLengthRow(); row++) {
            for (int col = 0; col < gameField.getLengthCol(); col++) {
                if (gameField.isOccupiedByXorO(row, col, xOrO)) {
                    counter++;

                    if (counter == 2) {

                        for (int colTemp = 0; colTemp < gameField.getLengthCol(); colTemp++) {

                            if (gameField.isNotOccupied(row, colTemp)) {
                                gameField.setO(row, colTemp);
                                Party.counterXandO++;
                                Party.moveCounter++;
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
    public static void checkColsAndMove(Field gameField, String xOrO) {
        int counter = 0;

        for (int col = 0; col < gameField.getLengthCol(); col++) {
            for (int row = 0; row < gameField.getLengthRow(); row++) {

                if (gameField.isOccupiedByXorO(row, col, xOrO)) {
                    counter++;

                    if (counter == 2) {

                        for (int rowTemp = 0; rowTemp < gameField.getLengthRow(); rowTemp++) {

                            if (gameField.isNotOccupied(rowTemp, col)) {
                                gameField.setO(rowTemp, col);
                                Party.counterXandO++;
                                Party.moveCounter++;
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
    public static void checkDiagonals(Field gameField, String xOrO) {

        if (gameField.isNotOccupied(0, 1) && gameField.isOccupiedByXorO(1, 2, xOrO) && gameField.isOccupiedByXorO(2, 3, xOrO)) {
            gameField.setO(0, 1);
            Party.counterXandO++;
            Party.moveCounter++;
            return;
        }

        if (gameField.isOccupiedByXorO(0, 1, xOrO) && gameField.isNotOccupied(1, 2) && gameField.isOccupiedByXorO(2, 3, xOrO)) {
            gameField.setO(1, 2);
            Party.counterXandO++;
            Party.moveCounter++;
            return;
        }

        if (gameField.isOccupiedByXorO(0, 1, xOrO) && gameField.isOccupiedByXorO(1, 2, xOrO) && gameField.isNotOccupied(2, 3)) {
            gameField.setO(2, 3);
            Party.counterXandO++;
            Party.moveCounter++;
            return;
        }

        if (gameField.isNotOccupied(2, 1) && gameField.isOccupiedByXorO(1, 2, xOrO) && gameField.isOccupiedByXorO(0, 3, xOrO)) {
            gameField.setO(2, 1);
            Party.counterXandO++;
            Party.moveCounter++;
            return;
        }

        if (gameField.isOccupiedByXorO(2, 1, xOrO) && gameField.isNotOccupied(1, 2) && gameField.isOccupiedByXorO(0, 3, xOrO)) {
            gameField.setO(1, 2);
            Party.counterXandO++;
            Party.moveCounter++;
            return;
        }

        if (gameField.isOccupiedByXorO(2, 1, xOrO) && gameField.isOccupiedByXorO(1, 2, xOrO) && gameField.isNotOccupied(0, 3)) {
            gameField.setO(0, 3);
            Party.counterXandO++;
            Party.moveCounter++;
        }
    }
}
