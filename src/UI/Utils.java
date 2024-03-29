package UI;

import java.util.Scanner;

/**
 * Utilities for the UI.
 * Includes functions such as "clear" for clearing the output, and "input" 
 * for getting user input. 
 * Note that this class is not instantiated. Use the methods directly.
 */
public class Utils {
    public static void line() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String inputOneWord(String prompt) {
        System.out.print(prompt);
        
        Scanner s = new Scanner(System.in);
        // Not closing s because doing so will prevent me from reading System.in
        // ever again. 

        return s.next();
    }

    public static int inputOneInt(String prompt) {
        System.out.print(prompt);
        
        Scanner s = new Scanner(System.in);

        return s.nextInt();
    }
}
