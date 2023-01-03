import java.util.Arrays;

public class GameField {
    private String[][] gameField;
    //String x = "X";
    //String o = "O";

    /**
     * Constructor of the game field.
     */
    public GameField(int row, int col) {
        gameField = new String[row][col+2];
    }

    /**
     * This method outputs game field on the screen.
     */
    public void outputGameField() {
        System.out.println("=========");
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                /* Makes left and right borders of the game field.*/
                if (j == 0 || j == 4) {
                    gameField[i][j] = "|";
                } else if (gameField[i][j] != null && gameField[i][j].equals("X")) {
                    gameField[i][j] = "X";
                } else if(gameField[i][j] != null && gameField[i][j].equals("O")) {
                    gameField[i][j] = "O";
                } else {
                    gameField[i][j] = " ";
                }
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=========");
    }

    public void setX(int row, int col) {
        gameField[row][col] = "X";
        outputGameField();
    }

    public void setO(int row, int col) {
        gameField[row][col] = "O";
        outputGameField();

    }

    public int getLengthRow() {
        return gameField.length;
    }

    public int getLengthCol() {
        return gameField[0].length;
    }

    public boolean isNotOccupied(int row, int col) {
        return gameField[row][col].equals(" ");
    }

    public boolean isCellWithXorO(int row, int col, String xO) {
        return gameField[row][col].equals(xO);
    }
}
