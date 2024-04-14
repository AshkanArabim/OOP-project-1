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
        Log log = new Log(this.user.getUsername());
        
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
                filterCars(); // If the user enters 2, they wish to filter the cars based on condition.
                log.addLogEntry("filter cars", "");
            } 
            else if (command == 3) {
                System.out.println("TODO:");
                // int id = purchaseCar(); // If the user enters 3, they wish to purchase a car.
                // if (id != -1) {
                //     log.addLogEntry("purchase car", "User bought car " + id);
                // }
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

    // /**
    //  * Allows user to purchase a car.
    //  * @return The car ID if the purchase was successful, else -1.
    //  */
    // // FIXME: move this to the user UI class
    // private int purchaseCar() {
    //     User currentUser = (User) currentPerson; // Cast the currentUser to a User type.
    //     while(true) {
    //         Utils.line();

    //         // Display the current balance of the user.
    //         System.out.println("Your balance is " + currentUser.getBalance());
            
    //         // Available options.
    //         System.out.println("Options:");
    //         System.out.println("# - Enter ID of desired car"); 
    //         System.out.println("0 - Go back");

    //         // Prompt the user for the ID of desired car.
    //         int command = Utils.inputOneInt("Enter ID of desired car: ");

    //         Utils.clear();
            
    //         if (command == 0) {
    //             return -1; // If the user enters 0, they wish to go back.
    //         }
    //         else if (command == -1) {
    //             System.out.println("Invalid command"); // In case the user enters an invalid command.
    //         }
    //         else if (command < 0 || command > cars.size()) {
    //             System.out.println("Invalid car ID"); // In case the user enters an invalid ID.
    //         }
    //         else {
    //             Car desiredCar = cars.get(command - 1); // Obtain the car the user wishes to purchase.

    //             // In case desired car is out of stock, inform the user.
    //             if(desiredCar.getVehiclesRemaining() == 0) {
    //                 System.out.println("Sorry,\n" + desiredCar + "\nis out of stock :(");
    //                 continue;
    //             }
    //             // Verify the user has sufficient funds.
    //             double subTotal = desiredCar.getPrice();

    //             // Apply discount if user is a member
    //             if (currentUser.getIsMember()) {
    //                 subTotal = Math.round((subTotal - (.10 * subTotal)) * 100.0) / 100.0;
    //             }
    //             // Add taxes
    //             double total = Math.round((subTotal + (.0625 * subTotal)) * 100.0) / 100.0;
    //             System.out.println("Total: " + total + "\nSubtotal: " + subTotal);
    //             if (currentUser.getBalance() >= total) {

    //                 // Confirm the user wants to proceed with the purchase.
    //                 if(!confirmPurchase(desiredCar)) {
    //                     continue;
    //                 }

    //                 // Create a receipt since the desired vehicle is in stock, the user has sufficient funds, and the user wishes to proceed.
    //                 Ticket receipt = new Ticket(desiredCar.getType(), desiredCar.getModel(), desiredCar.getYear(), desiredCar.getColor(), currentUser.getFirstName() + " " + currentUser.getLastName());
                    
    //                 // Add the receipt to the user's list of tickets.
    //                 currentUser.addTicket(receipt);

    //                 // Add the receipt to the shop's list of tickets.
    //                 allTickets.add(receipt);

                    
    //                 // Update the user's number of cars purchased.
    //                 currentUser.setCarsPurchased(currentUser.getCarsPurchased() + 1);
                    
    //                 // Update the number of vehicles remaining for the car object.
    //                 desiredCar.setVehiclesRemaining(desiredCar.getVehiclesRemaining() - 1);
                    
    //                 // Subtract 1 from the count of cars in the CSV file.
    //                 decrementCarFromCSV(desiredCar.getCarID());

    //                 // Update the user's balance.
    //                 currentUser.setBalance(Math.round((currentUser.getBalance() - total) * 100.0) / 100.0);
    //                 updateBalanceInCSV(currentUser);

    //                 // Inform the user they successfully purchased the car.
    //                 System.out.println("Successfully purchased:\n" + desiredCar);
    //                 return desiredCar.getCarID();

    //             }
    //             // Inform the user that they do not possess sufficient funds.
    //             else {
    //                 System.out.println("Sorry,\n" + desiredCar + "\ncosts $" + desiredCar.getPrice() + " but you only have $" + currentUser.getBalance());
                    
    //             }
    //         }
    //     }
    // }

    // /**
    //  * Ensures the user wants to make the purchase.
    //  * @param desiredCar object of type Car that the user wishes to purchase.
    //  * @return True if the customer wishes to proceed with the purchase, False if the user changed their mind.
    //  */
    // // FIXME: move this to the user UI class
    // private boolean confirmPurchase(Car desiredCar) {
    //     while (true) {
    //         // Available options.
    //         System.out.println("Are you sure you want to purchase?\n" + desiredCar);
    //         System.out.println("1 - Yes");
    //         System.out.println("2 - No");

    //         // Prompt the user for input.
    //         int decision = Utils.inputOneInt("Enter command: ");
    //         Utils.clear();

    //         if (decision == -1) {
    //             System.out.println("Invalid command"); // In case the user enters an invalid command.
    //             continue;
    //         }
    //         else if (decision == 1) {return true;} // If the user enters 1, they wish to proceed with the purchase.
    //         else if (decision == 2) {return false;} // If the user enters 2, they wish to cancel the purchase.
    //     }
    // }

    // /**
    //  * Display tickets of the current user.
    //  */
    // // FIXME: move this to user UI class
    // private void viewTickets() {
    //     User currentUser = (User) currentPerson;
    //     currentUser.viewTickets();
    // }
}
