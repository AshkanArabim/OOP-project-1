/*
 * Academic Integrity Statement
 * This work is to be done as a team. It is not permitted to share, reproduce, or alter
 * any part of this assignment for any purpose. Students are not permitted to share
 * code, upload this assignment online in any form, or view/receive/modify code
 * written from anyone else. This assignment is part of an academic course at The
 * University of Texas at El Paso and a grade will be assigned for the work produced
 * individually by the student.
 * 
 * Names of individual(s) who contributed to this work:
 * Ashkan Arabi
 * James Newson
 * Ruben Martinez
 * 
 * Date: April 11th, 2024
 * Course: CS 3331 Advanced Object Oriented Programming
 * Instructor: Bhanukiran Gurijala
 * Programming Assignment : 2
 * 
 * Description: The system offers both brand new and used cars for a car dealership Mine Cars. It provides
 * a wide variety of different models with varying prices and mileage to satisfy customers. Customers have budgets
 * and our system maintains and tracks a portfolio for each customer via a CSV file. In addition, customers
 * can sort through our vehicles, and admins can view tickets for every car purchased from our dealership. Finally,
 * memebrs can opt for a membership to recieve discounts or better interest rates.
 * 
 */

import UI.*;

/**
 * This is the main runner class that is responsible for signing in users and admins, and also selling cars.
 * @author Ashkan Arabi
 * @author James Newson
 * @author Ruben Martinez
 * @version 2.0
 */
public class RunShop {

    /**
     * Main method.
     * 
     * @param args
     */    
    public static void main(String[] args) {
        loginScreen();
    }

    /**
     * Initial login screen that prompts user to enter credentials and admit them as admin or user accordingly.
     */
    private static void loginScreen() {
        Utils.clear();

        // Loop so it prompts again when user signs out.
        while (true) {
            Utils.line();
            System.out.println("Welcome to Mine Cars!");
            System.out.println("Note: for admin login, use 'admin' for both username & password.");
            System.out.println();
    
            // Username and password prompts.
            String username = Utils.inputOneWord("Username: ");
            String password = Utils.inputOneWord("Password: ");
            
            // Clearing before error messages so user has a chance to see them.
            Utils.clear();
            

            // try logging in, print incorrect password message if failed
            if (! login(username, password)) {
                System.out.println("Username or password incorrect.");
            } else {
                System.out.println("Logged out.");
            }
        }
    }

    /**
     * Tries to log in as user or admin based on given credentials.
     * 
     * @return true if login successful, false otherwise.
     */
    private static boolean login(String username, String password) {
        return (
            UserUI.login(username, password) ||
            AdminUI.login(username, password)
        );
    }
}