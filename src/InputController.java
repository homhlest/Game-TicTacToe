
/**
 * This class checks user input.
 */
public class InputController {

    /**
     * This method checks user input.
     *
     * @param c input of coordinate.
     * @return is input in the range (1,3) or not.
     */
    public static boolean isInRange(int c) {
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
    public static boolean isNumber(String coor1Input) {
        for (int i = 0; i < coor1Input.length(); i++) {
            if ((coor1Input.charAt(i) < 48) || (coor1Input.charAt(i) > 57)) {
                System.out.println("You should enter two numbers.");
                return false;
            }
        }
        return true;
    }
}
