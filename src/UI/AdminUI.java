package UI;

import entity.Ticket;
import entity.User;

public class AdminUI {
    
    
    /**
     * Login screen for admins that enable them to display all users and view all tickets.
     */
    // FIXME: move this to admin UI class
    private static void adminLogin() {
        while (true) {
            // Available options for Admins.
            Utils.line();
            System.out.println("[ADMIN MODE]");
            System.out.println("Options:");
            System.out.println("1 - Display all users"); // Admins may display all users.
            System.out.println("2 - View all Tickets"); // Admins may view all tickets.
            System.out.println("0 - Sign out");

            // Prompt admin for desired action.
            int command = Utils.inputOneInt("Enter command: ");

            Utils.clear();

            if (command == 1) {
                System.out.println("TODO:");
                // displayAllUsers();  // If the admin enters 1, they wish to display all users.
            } 
            else if (command == 0) {
                return;
            } 
            else if (command == 2) {
                System.out.println("TODO:");
                // viewAllTickets(); // If the admin enters 2, they wish to view all tickets.
            }
            else {
                System.out.println("Invalid command"); // In case the user entered an invalid command.
            }
        }
    }

    // /**
    //  * Displays all users from the CSV file.
    //  */
    // // FIXME: move this to admin UI class
    // private static void displayAllUsers() {
    //     for (User user : users.values()) {
    //         System.out.println(user);
    //     }

    //     System.out.println("");
    //     System.out.println("Row content:");
    //     System.out.println("[First \t Last \t Balance \t Cars purchased \t Is member? \t username]");
    // }

    // /**
    //  * Display all the tickets of the shop.
    //  */
    // // FIXME: move this to admin ui class
    // private static void viewAllTickets() {
    //     for(Ticket ticket : allTickets) {
    //         System.out.println(ticket);
    //     }
    //     System.out.println("");
    //     System.out.println("Row content:");
    //     System.out.println("[Type \t Model \t Year \t Color \t Owner]");
    // }
}
