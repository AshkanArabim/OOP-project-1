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
}
