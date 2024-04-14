package UI;

import datautils.CarCSVHandler;
import datautils.Log;
import datautils.UserCSVHandler;
import entity.Ticket;
import entity.User;
import vehicles.Car;
import UI.UI;


/**
 * Login screen for users that enable them to display all cars, filter cars, purchase a car, view tickets, and sign out.
 * @see UI.java for other available fields
 */
public class UserUI extends UI{

    // static fields

    /**
     * Try to log in as user with given credentials.
     * This is the ONLY available public method of this class.
     * @return true if login successful, false otherwise.
     */
    public static boolean login (String username, String password) {
        User currentUser = USERDATA.validateAndGetUser(username, password);
        if (currentUser != null) {
            UserUI ui = new UserUI(currentUser);
            ui.menuLoop(); // start user's interface
            return true; // login worked
        }

        return false; // login failed
    }

    // instance fields

    /**
     * Private constructor to use with login().
     */
    private UserUI(User user) {
        super(user);
    }

    // FIXME: move this to user UI class
    private void menuLoop() {
        Log log = new Log(this.person.getUsername());
        
        log.addLogEntry("login", "");

        while (true) {
            // Avaiable options for Users.
            Utils.line();
            System.out.println("Options:");
            System.out.println("1 - Display all cars"); // Users may display all cars.
            System.out.println("2 - Filter Cars (used / new)"); // Users may filter cars based on used/new condition.
            System.out.println("3 - Purchase a car"); // Users may purchase a car.
            System.out.println("4 - View Tickets"); // Users may view their tickets.
            System.out.println("0 - Sign out"); // Users may sign out.

            // Prompt user for desired action.
            int command = Utils.inputOneInt("Enter command: ");

            Utils.clear();

            if (command == 1) {
                // display all cars
                System.out.println(CARDATA);
                log.addLogEntry("view cars", "");
            } 
            else if (command == 2) {
                // filter cars (used / new)
                filterCars();
                log.addLogEntry("filter cars", "");
            } 
            else if (command == 3) {
                // purchase a car
                int id = purchaseCar();
                if (id != -1) {
                    log.addLogEntry("purchase car", "User bought car " + CARDATA.getCarStringByID(id));
                }
            } 
            else if (command == 4) {
                System.out.println("TODO:");
                // viewTickets(); // If the user enters 4, they wish to view their tickets.
                // log.addLogEntry("view tickets", "");
            } 
            else if (command == 0) {
                log.addLogEntry("logout", "");
                return; // returning true to show that login was a success
            } 
            else {
                System.out.println("Invalid command"); // In case the user entered an invalid command.
            }
        }
    }

    /**
     * Allows user to display new cars or used cars.
     */
    // FIXME: move this to the user UI class
    private void filterCars() {
        while(true) {
            // Available options for filtering cars.
            Utils.line();
            System.out.println("Options:");
            System.out.println("1 - Display New Cars"); 
            System.out.println("2 - Display Used Cars");
            System.out.println("3 - Go back"); 

            // Prompt the user for input.
            int command = Utils.inputOneInt("Enter command: ");

            Utils.clear();

            switch(command) {
                case(1): {
                    System.out.println(CARDATA.getNewCarsList());
                } break;
                case(2): {
                    System.out.println(CARDATA.getUsedCarsList());
                } break; // If the user enters 2, they wish to display used cars.
                case(3): return; // If the user enters 3, they wish to exit this menu.
                default: System.out.println("Invalid command"); continue; // In case the user enters an invalid command.
            }
        }
    }

    /**
     * Allows user to purchase a car.
     * @return The car ID if the purchase was successful, else -1.
     */
    // FIXME: move this to the user UI class
    private int purchaseCar() {
        User currentUser = (User) this.person; // Cast the currentUser to a User type.
        while(true) {
            Utils.line();

            // Display the current balance of the user.
            System.out.println("Your balance is " + currentUser.getBalance());
            
            // Available options.
            System.out.println("Options:");
            System.out.println("# - Enter ID of desired car"); 
            System.out.println("0 - Go back");

            // Prompt the user for the ID of desired car.
            int id = Utils.inputOneInt("Enter ID of desired car: ");

            Utils.clear();
            
            if (id == 0) {
                return -1; // If the user enters 0, they wish to go back.
            } 
            
            // TODO - this system may need to be revised to provide more detailed info
            // I had to simplify these errors to make it easier to decouple the UI from the data
            double subTotalOrStatus = CARDATA.validatePurchase(id - 1, currentUser);

            if (subTotalOrStatus < 0) {
                if (subTotalOrStatus == -1) {
                    System.out.println("Invalid car ID!"); // In case the user enters an invalid ID.
                    // note that this also handles the case of id being -1 due to an invalid format
                } else if (subTotalOrStatus == -2) {
                    System.out.println("Sorry, that car is out of stock :(");
                } else if (subTotalOrStatus == -3) {
                    System.out.println("Insufficient funds!");
                }

                continue;
            }

            // Confirm the user wants to proceed with the purchase.
            if(!confirmPurchase(id - 1)) {
                continue;
            }

            int purchaseStatus = CARDATA.purchaseCar(id - 1, currentUser, subTotalOrStatus);

            if (purchaseStatus == -1) {
                System.out.println("Purchase failed...");
            } else {
                // Inform the user they successfully purchased the car.
                System.out.println("Successfully purchased:\n" + CARDATA.getCarStringByID(id - 1));
                return id - 1;
            }

        }
    }

    /**
     * Ensures the user wants to make the purchase.
     * @param id index (real, so subtract by 1 if passing from purchaseCar) of the car the user wants to buy
     * @return True if the customer wishes to proceed with the purchase, False if the user changed their mind.
     */
    private boolean confirmPurchase(int carID) {
        while (true) {
            // Available options.
            System.out.println("Are you sure you want to purchase?\n" + CARDATA.getCarStringByID(carID));
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            // Prompt the user for input.
            int decision = Utils.inputOneInt("Enter command: ");
            Utils.clear();

            if (decision == 1) {
                return true;
            } else if (decision == 2) {
                return false;
            } else {
                System.out.println("Invalid command"); // In case the user enters an invalid command.
            }
        }
    }

    // /**
    //  * Display tickets of the current user.
    //  */
    // // FIXME: move this to user UI class
    // private void viewTickets() {
    //     User currentUser = (User) currentPerson;
    //     currentUser.viewTickets();
    // }
}
