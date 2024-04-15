package UI;

import datautils.Log;
import entity.Admin;

public class AdminUI extends UI{

    // static fields

    public static boolean login (String username, String password) {
        if (username.equals("admin") && password.equals("admin")){
            // Admin and Adminson are temporary first and last names.
            Admin admin = new Admin(0, "Admin", "Adminson", username, password);
            AdminUI ui = new AdminUI(admin);
            ui.menuLoop();

            return true; // login successful
        }

        return false; // login failed
    }

    // instance fields

    private AdminUI(Admin admin) {
        super(admin);
    }

    @Override
    protected void menuLoop() {
        Log log = Log.getInstance(this.person.getUsername());
        
        log.addLogEntry("login", "");

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
                // print all users
                System.out.println(USERDATA);
            } else if (command == 2) {
                // print all ticktes                
                System.out.println("Tickets:");
                System.out.println(USERDATA.getAllTicketsList());
            } else if (command == 0) {
                return;
            } else {
                System.out.println("Invalid command"); // In case the user entered an invalid command.
            }
        }
    }
}
