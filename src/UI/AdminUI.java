package UI;
import java.io.IOException;
import java.util.Arrays;
import datautils.CarNotFoundException;
import datautils.Log;
import entity.Admin;

/**
 * Login screen for admins that enable them to interact with the system.
 */
public class AdminUI extends UI{

    /**
     * Private constructor to use with login().
     * @param admin The admin to be logged in.
     */
    private AdminUI(Admin admin) {
        super(admin);
    }
    
    /**
     * Try to log in as admin with given credentials.
     * This is the ONLY available public method of this class.
     * @param username Username of the user.
     * @param password Password of the user.
     * @return True if login successful, false otherwise.
     */
    public static boolean login (String username, String password) {

        if (username.equals("admin") && password.equals("admin")){

            // Admin and Adminson are temporary first and last names.
            Admin admin = new Admin(0, "Admin", "Adminson", username, password);
            AdminUI ui = new AdminUI(admin);
            ui.menuLoop();

            return true; // Login successful.
        }

        return false; // Login failed.
    }

    
    /**
     * Display the options for admins.
     */
    @Override
    protected void menuLoop() {
        Log log = Log.getInstance(this.person.getUsername());
        
        log.addLogEntry("login", "");

        while (true) {
            try {
                // Available options for Admins.
                Utils.line();
                System.out.println("[ADMIN MODE]");
                System.out.println("Options:");
                System.out.println("1 - Display all users");
                System.out.println("2 - View all Tickets");
                System.out.println("3 - Add new car");
                System.out.println("4 - Display all cars");
                System.out.println("5 - Get Revenue by ID");
                System.out.println("6 - Get Revenue by Car Type");
                System.out.println("7 - Remove a car");
                System.out.println("8 - Add new user");
                System.out.println("0 - Sign out");
    
                // Prompt admin for desired action.
                int command = Utils.inputOneInt("Enter command: ");
    
                Utils.clear();
    
                if (command == 1) {
                    System.out.println(USERDATA);
                    log.addLogEntry("print all users", "");
                } 
                else if (command == 2) {
                    System.out.println("Tickets:");
                    System.out.println(USERDATA.getAllTicketsList());
                    log.addLogEntry("view all tickets", "");
                } 
                else if (command == 3) {
                    int newCarID = addCarLoop();

                    if (newCarID > 0) {
                        log.addLogEntry("add car: ", "" + CARDATA.getCarByID(newCarID));
                    }
                } 
                else if (command == 4) {
                    System.out.println(CARDATA);
                    log.addLogEntry("view cars", "");
                } 
                else if (command == 5) {
                    int id = Utils.inputOneInt("Enter ID of car: ");
                    try {

                        if (CARDATA.validateID(id)) {
                            System.out.println(USERDATA.getRevenueByID(id));
                            log.addLogEntry("view revenue of car: " + id, "");
                        }
                        else {
                            Utils.invalidInput();
                        }
                    }
                    catch (CarNotFoundException cnfe) {
                        System.out.println("Could not find that car.");
                    }
                } 
                else if (command == 6) {
                    String[] options = new String[] {"Hatchback", "Sedan", "SUV", "Pickup"};
                    String type = Utils.inputOneLineLoop(
                        "Enter car type [" + String.join("|", options) + "]: ",
                        options);
                    System.out.println(USERDATA.getRevenueByType(type));
                    log.addLogEntry("view revenue of car type: " + type, "");
                } 
                else if (command == 7) {
                    int id = Utils.inputOneInt("Enter ID of car to remove: ");
                    try {
                        if (CARDATA.validateID(id)) {
                            CARDATA.removeCar(id);
                            log.addLogEntry("remove car: " + id, "");
                            Utils.clear();
                            System.out.println("Successfully removed car: " + id);
                        }
                        else {
                            Utils.clear();
                            Utils.invalidInput();
                        }
                    }
                    catch (CarNotFoundException cnfe) {
                        System.out.println("Could not find that car.");
                    }
                } 
                else if (command == 8) {
                    String username = Utils.inputOneWord("Enter new user's username: ");
                    if (USERDATA.userNameExists(username)) {
                        System.out.println(username + " already exists.");
                    }
                    else if(USERDATA.addUser(username)) {
                            System.out.println("Successfully added user: " + username);
                            log.addLogEntry("added user " + username, "");
                    }
                    else {
                        Utils.invalidInput();
                    }
                } 
                else if (command == 0) {
                    log.addLogEntry("logout", "");
                    return;
                } 
                else {
                    throw new IOException();
                }
            } catch (IOException e) {
                Utils.clear();
                Utils.invalidInput();
            }
        }
    }

    /**
     * Starts a the procedure to add a new car.
     * @return the ID of the car that was added.
     */
    private int addCarLoop() {
        String[] cols = CARDATA.getCsvColumns();
        String[] carAttrs = new String[cols.length];

        for (int i = 0; i < cols.length; i++) {
            if (cols[i].equals("Capacity")) {
                int[] options = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

                // Convert options to string to show the user.
                String[] stringOptions = Arrays.stream(options)
                        .mapToObj(String::valueOf)
                        .toArray(String[]::new);
                
                carAttrs[i] = "" + Utils.inputOneIntLoop(
                        "Enter " + cols[i] + " [" + String.join("|", stringOptions) + "]: ", options, false
                );
            } 
            else if (cols[i].equals("Car Type")) {
                String[] options = new String[] {"Hatchback", "Sedan", "SUV", "Pickup"};
                carAttrs[i] = Utils.inputOneLineLoop(
                        "Enter " + cols[i] + " [" + String.join("|", options) + "]: ", options
                );
            } 
            else if (cols[i].equals("Cars Available")) {
                carAttrs[i] = "" + Utils.inputOneIntLoop(
                    "Enter " + cols[i] + ": ", new int[] {}, false
                );
            }
            else if (cols[i].equals("Condition")) {
                String[] options = new String[] {"New", "Used"};
                carAttrs[i] = Utils.inputOneLineLoop(
                    "Enter " + cols[i] + " [" + String.join("|", options) + "]: ", options
                );
            } 
            else if (cols[i].equals("ID")) {
                // ID will be dynamically determined later, so we don't care.
                carAttrs[i] = "-1";
            } 
            else if (cols[i].equals("Year")) {
                carAttrs[i] = "" + Utils.inputOneIntLoop(
                    "Enter " + cols[i] + ": ", new int[] {}, false
                );
            } 
            else if (cols[i].equals("Price")) {
                carAttrs[i] = "" + Utils.inputOneDoubleLoop(
                    "Enter " + cols[i] + ": ", 0, Double.POSITIVE_INFINITY
                );
            } 
            else if (cols[i].equals("Transmission")) {
                String[] options = new String[] {"Manual", "Automatic"};
                carAttrs[i] = Utils.inputOneLineLoop(
                    "Enter " + cols[i] + " [" + String.join("|", options) + "]: ", options
                );
            } 
            else if (cols[i].equals("Fuel Type")) {
                String[] options = new String[] {"Gasoline", "Electric", "Hybrid", "Diesel"};
                carAttrs[i] = Utils.inputOneLineLoop(
                    "Enter " + cols[i] + " [" + String.join("|", options) + "]: ", options
                );
            }
            else if (cols[i].equals("hasTurbo")) {
                String[] options = new String[] {"Yes", "No"};
                carAttrs[i] = Utils.inputOneLineLoop(
                    "Enter " + cols[i] + " [" + String.join("|", options) + "]: ", options
                );
            } 
            else {
                // All other string inputs caught here.
                carAttrs[i] = Utils.inputOneLine("Enter " + cols[i] + ": ");
            }
        }

        int newCarID = CARDATA.addCar(carAttrs);

        if (newCarID == -1) {
            System.out.println("A car with these attributes already exists!");
            return -1;
        }
        
        System.out.println("Car added with ID: " + newCarID);
        return newCarID;
    }

}