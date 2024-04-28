package UI;
import java.io.IOException;
import datautils.Log;
import entity.User;

/**
 * Login screen for users that enable them to interact with the system.
 */
public class UserUI extends UI{

    /**
     * Private constructor to use with login().
     * @param user The user to be logged in.
     */
    private UserUI(User user) {
        super(user);
    }
    
    /**
     * Try to log in as user with given credentials.
     * This is the ONLY available public method of this class.
     * @param username Username of the user.
     * @param password Password of the user.
     * @return True if login successful, false otherwise.
     */
    public static boolean login (String username, String password) {
        User currentUser = USERDATA.validateAndGetUser(username, password);

        if (currentUser != null) {
            UserUI ui = new UserUI(currentUser);
            ui.menuLoop(); // Start user's interface.
            return true; // Login succeeded.
        }

        return false; // Login failed.
    }

    /**
     * Display the options for users.
     */
    @Override
    protected void menuLoop() {

        Log log = Log.getInstance(this.person.getUsername());
        
        log.addLogEntry("login", "");

        while (true) {
            try {
                Utils.line();
                // Show available options to users.
                System.out.println("Options:");
                System.out.println("1 - Display all cars");
                System.out.println("2 - Filter Cars (used / new)");
                System.out.println("3 - Purchase a car");
                System.out.println("4 - View Tickets");
                System.out.println("5 - Return a car");
                System.out.println("0 - Sign out");

                // Prompt user for desired action.
                int command = Utils.inputOneInt("Enter command: ");

                Utils.clear();

                if (command == 1) {
                    System.out.println(CARDATA);
                    log.addLogEntry("view cars", "");
                } 
                else if (command == 2) {
                    filterCars();
                    log.addLogEntry("filter cars", "");
                } 
                else if (command == 3) {
                    int id = purchaseCar();
                    if (id != -1) {
                        log.addLogEntry("purchase car", "User bought car " + CARDATA.getCarByID(id));
                    }
                } 
                else if (command == 4) {
                    User currentUser = (User) this.person;
                    System.out.println("Tickets:");
                    System.out.println(currentUser.getTicketsList());
                    log.addLogEntry("view tickets", "");
                }
                else if (command == 5) {
                    User currentUser = (User) this.person;
                    if (currentUser.getCarsPurchased() == 0) {
                        System.out.println("You do not own any cars.");
                    }
                    else {
                        System.out.println("Tickets:");
                        System.out.println(currentUser.getTicketsList());
                        int carIDToReturn = Utils.inputOneInt("Enter ID of car to return: ");
                        if (USERDATA.returnCar(currentUser.getUsername(), carIDToReturn)) {
                            System.out.println("Successfully Returned Car!");
                            log.addLogEntry(currentUser.getUsername() + " return car " + carIDToReturn, "");
                            CARDATA.incrementCarCount(carIDToReturn);
                        }
                    }
                }
                else if (command == 0) {
                    log.addLogEntry("logout", "");
                    return;
                } 
                else {
                    throw new IOException();
                }
            } 
            catch (IOException e) {
                Utils.clear();
                Utils.invalidInput();
            }
        }

    }

    /**
     * Allows user to display new cars or used cars.
     */
    private void filterCars() {

        while(true) {
            try {
                // Available options for filtering cars.
                Utils.line();
                System.out.println("Options:");
                System.out.println("1 - Display New Cars"); 
                System.out.println("2 - Display Used Cars");
                System.out.println("0 - Go back"); 
    
                // Prompt the user for input.
                int command = Utils.inputOneInt("Enter command: ");
    
                Utils.clear();

                if (command == 1) {
                    System.out.println(CARDATA.getNewCarsList());
                } 
                else if (command == 2) {
                    System.out.println(CARDATA.getUsedCarsList());
                } 
                else if (command == 0) {
                    return;
                }
                else {
                    throw new IOException();
                }
            } 
            catch (IOException e) {
                Utils.clear();
                Utils.invalidInput();
            }
        }
        
    }

    /**
     * Allows user to purchase a car.
     * @return The car ID if the purchase was successful, else -1.
     */
    private int purchaseCar() {

        User currentUser = (User) this.person; // Cast the currentUser to a User type.
        while(true) {
            try {
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

                double[] totalAndSubTotalOrStatus = CARDATA.validatePurchase(id, currentUser);
    
                if (totalAndSubTotalOrStatus[0] < 0) {
                    if (totalAndSubTotalOrStatus[0] == -1) {
                        System.out.println("Invalid car ID!"); // In case the user enters an invalid ID.
                    } 
                    else if (totalAndSubTotalOrStatus[0] == -2) {
                        System.out.println("Sorry, that car is out of stock :(");
                    } 
                    else if (totalAndSubTotalOrStatus[0] == -3) {
                        System.out.println("Insufficient funds!");
                    }
                    continue;
                }
    
                // Confirm the user wants to proceed with the purchase.
                double subtotal = totalAndSubTotalOrStatus[0];
                double total = totalAndSubTotalOrStatus[1];
                if(!confirmPurchase(id, subtotal, total)) {
                    continue;
                }
    
                int purchaseStatus = CARDATA.purchaseCar(id, currentUser, total);
    
                if (purchaseStatus == -1) {
                    System.out.println("Purchase failed...");
                } 
                else {
                    // Inform the user they successfully purchased the car.
                    System.out.println("Successfully purchased:\n" + CARDATA.getCarByID(id));
                    return id;
                }
            } 
            catch (IOException e) {
                Utils.clear();
                Utils.invalidInput();
            }
        }

    }

    /**
     * Ensures the user wants to make the purchase.
     * @param id index (real, so subtract by 1 if passing from purchaseCar) of the car the user wants to buy
     * @return True if the customer wishes to proceed with the purchase, False if the user changed their mind.
     */
    private boolean confirmPurchase(int carID, double subtotal, double total) {

        while (true) {
            try {
                System.out.println("Total: " + total);
                System.out.println("Subtotal: " + subtotal);
                System.out.println();
    
                System.out.println("Are you sure you want to purchase?");
                System.out.println(CARDATA.getCarByID(carID));
                
                // Available options.
                Utils.line();
                System.out.println("1 - Yes");
                System.out.println("2 - No");
    
                // Prompt the user for input.
                int decision = Utils.inputOneInt("Enter command: ");
                Utils.clear();
    
                if (decision == 1) {
                    return true;
                } 
                else if (decision == 2) {
                    return false;
                } 
                else {
                    throw new IOException();
                }
            } 
            catch (IOException e) {
                Utils.clear();
                Utils.invalidInput();
            }
        }

    }

}