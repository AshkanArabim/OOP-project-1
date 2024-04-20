package UI;
// import statements
/************************************************************************/
import java.io.IOException;
import java.util.Scanner;
/************************************************************************/

/**
 * Utilities for the UI.
 * Includes functions such as "clear" for clearing the output, and "input" 
 * for getting user input. 
 * Note that this class is not instantiated. Use the methods directly.
 */
public class Utils {
    /**
     * Dashes to enhance printing aesthetics.
     */
    public static void line() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");
    }

    /**
     * Extra dashes to further enhance printing aesthetics.
     */
    public static void longerLine() {
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    /**
     * Clears the terminal.
     */
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Prints a message telling the user their input is invalid.
     * Useful to keep all invalid input messages consistent instead of manually
     * hardcoding them every time.
     */
    public static void invalidInput() {
        System.out.println("Invalid input!");
    }

    /**
     * Prompts the user to enter a word.
     * @param prompt Message displaying to the user.
     * @return The user's decision.
     */
    public static String inputOneWord(String prompt) {
        System.out.print(prompt);
        
        Scanner s = new Scanner(System.in);
        // Not closing s because doing so will prevent me from reading System.in
        // ever again. 

        return s.next();
    }

    /**
     * Prompts the user to enter a line (everything until \n)
     * @param prompt Message displaying to the user.
     * @return The user's response.
     */
    public static String inputOneLine(String prompt) {
        System.out.print(prompt);
        
        Scanner s = new Scanner(System.in);
        // Not closing s because doing so will prevent me from reading System.in
        // ever again. 

        return s.nextLine();
    }

    /**
     * Prompts the user to enter an integer.
     * @param prompt Message displaying to the user.
     * @return The user's decision.
     */
    public static int inputOneInt(String prompt) throws IOException{
        System.out.print(prompt);
        
        Scanner s = new Scanner(System.in);

        try {
            return s.nextInt();
        }
        catch (java.util.InputMismatchException e) {
            // in case the user inputs something other than an integer
            throw new IOException();
        }
    }

    /**
     * Prompts the user to enter an integer.
     * @param prompt Message displaying to the user.
     * @return The user's decision.
     */
    public static double inputOneDouble(String prompt) throws IOException{
        System.out.print(prompt);
        
        Scanner s = new Scanner(System.in);

        try {
            return s.nextDouble();
        }
        catch (java.util.InputMismatchException e) {
            // in case the user inputs something other than an integer
            throw new IOException();
        }
    }

    /**
     * Prompts the user to enter an integer, and will repeat if the response is invalid.
     * @param prompt Message displaying to the user.
     * @param options Valid integer options (useful in UI selection menus). 
     *                Pass an int array of zero length to have unlimited range.
     * @param negativeAllowed Whether or not negative integers are accepted.
     * @return The user's decision.
     */
    public static int inputOneIntLoop(String prompt, int[] options, boolean negativeAllowed) {
        while (true) {
            try {
                int response = inputOneInt(prompt);

                // if negative not allowed but user gave a negative, error
                if (!negativeAllowed && response < 0){
                    throw new IOException();
                }

                // if no option restrictions, return response immediately
                if (options.length == 0) {
                    return response;
                }

                // only return if response is in list of options
                for (int option : options) {
                    if (response == option) {
                        return response;
                    }
                }

                throw new IOException(); // if response is not a valid option
            } catch (IOException e) {
                // this will be added before the next prompt
                System.out.print("[INVALID INPUT] ");
            }
        }
    }

    /**
     * Prompts the user to enter a String from a list of options
     * If you don't want to pass a list of options just use `inputOneLine`.
     * @param prompt Message displaying to the user.
     * @param options Valid integer options (useful in UI selection menus). 
     * @return The user's decision.
     */
    public static String inputOneLineLoop(String prompt, String[] options) {
        while (true) {
            try {
                String response = inputOneLine(prompt);

                // only return if response is in list of options
                for (String option : options) {
                    if (response.equals(option)) {
                        return response;
                    }
                }

                throw new IOException();
            } catch (IOException e) {
                // this will be added before the next prompt
                System.out.print("[INVALID INPUT] ");
            }
        }
    }

    /**
     * Prompts the user to enter a double, and will repeat if the response is invalid.
     * @param prompt Message displaying to the user.
     * @param lowerRange Inclusive lower range of allowed inputs.
     *                   To ignore, pass a Double.NEGATIVE_INFINITY
     * @param upperRange Inclusive upper range of allowed inputs.
     *                   To ignore, pass a Double.POSITIVE_INFINITY
     * @return The user's decision.
     */
    public static double inputOneDoubleLoop(String prompt, double lowerBound, double upperBound) {
        while (true) {
            try {
                double response = inputOneDouble(prompt);

                // check if the bounds match
                if (response > upperBound || response < lowerBound) {
                    throw new IOException();
                }

                return response;
            } catch (IOException e) {
                // this will be added before the next prompt
                System.out.print("[INVALID INPUT] ");
            }
        }
    }
}
