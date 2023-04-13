/**
 * This class checks user input.
 */
public class InputController {

    /**
     * This method checks user input.
     *
     * @param input is input of coordinate.
     * @return is input correct or not.
     */
    public static boolean isInputCorrect(String input) {
        if (isInRange(input) && isNumber(input)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks user input.
     *
     * @param input input of coordinate.
     * @return is input in the range (1,3) or not.
     */
    public static boolean isInRange(String input) {
        if (!input.matches("[1-3]")) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        return true;
    }

    /**
     * This method checks if the input is a number.
     *
     * @param input input of coordinate.
     * @return is number.
     */
    public static boolean isNumber(String input) {
        if (!input.matches("\\d")) {
            System.out.println("You should enter two numbers.");
            return false;
        }
        return true;
    }
}
