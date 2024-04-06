// import statements
/************************************************************************/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import vehicles.*;
import entity.*;
import UI.*;
/************************************************************************/


/**
 * This is the main runner class.
 * @author Ashkan Arabi
 * @author James Newson
 * @author Ruben Martinez
 * @version 1.0
 */
public class RunShop {

    // Global Variables.
    /************************************************************************/
    /**
     * Contains Car objects from the CSV files.
     */
    private static ArrayList<Car> cars = new ArrayList<Car>();

    /**
     * The person who is currently using the system.
     */
    private static Person currentPerson;

    /**
     * A string to the directory of the Car Data CSV file.
     */
    private static String carSourceCSV = "../data/car_data.csv";

    /**
     * A string to the directory of the User Data CSV file.
     */
    private static String userSourceCSV = "../data/user_data.csv";
    
    /**
     * A list containing all Tickets for each car purchased.
     */
    private static ArrayList<Ticket> allTickets = new ArrayList<>();

    /**
     * Hashmap to efficiently match the entered username in login prompt to a valid user in the database.
     */
    private static HashMap<String, User> users = new HashMap<String, User>();
    /************************************************************************/

    /**
     * Main method.
     * 
     * @param args
     */    
    public static void main(String[] args) {

        loadUsers(userSourceCSV); // Load the users from the CSV file.
        loadCars(carSourceCSV); // Load the cars from the CSV file.

        loginScreen();
        userLogin();
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
            System.out.println();
    
            // Username and password prompts.
            String username = Utils.inputOneWord("Username: ");
            String password = Utils.inputOneWord("Password: ");
            
            // Clearing before error messages so user has a chance to see them.
            Utils.clear();
            

            // Handling Admin login.
            // For now, assume the admin will use "admin" and "admin" as both the username and password.
            if (username.equals("admin") && password.equals("admin")) {
                // Admin and Adminson are temporary first and last names.
                currentPerson = new Admin(0, "Admin", "Adminson", username, password);
                adminLogin();
                currentPerson = null; // Reset the currentPerson so the next person may sign in.
                continue;
            }
            
            // Handling User login.
            User selected_user = users.getOrDefault(username, null);
            // If the username and password match.
            if (
                selected_user != null &&
                selected_user.getPassword().equals(password)
            ) {
                currentPerson = selected_user;
                userLogin();
                currentPerson = null; // Reset the currentPerson so the next person may sign in.
                System.out.println("Logged out.");
            } 
            // In case they don't match.
            else {
                System.out.println("Username or password incorrect.");
            }
        }
    }
    
    /**
     * Login screen for admins that enable them to display all users and view all tickets.
     */
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
                displayAllUsers();  // If the admin enters 1, they wish to display all users.
            } 
            else if (command == 0) {
                return;
            } 
            else if (command == 2) {
                viewAllTickets(); // If the admin enters 2, they wish to view all tickets.
            }
            else {
                System.out.println("Invalid command"); // In case the user entered an invalid command.
            }
        }
    }

    /**
     * Login screen for users that enable them to display all cars, filter cars, purchase a car, view tickets, and sign out.
     */
    private static void userLogin() {
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
                displayAllCars(); // If the user enters 1, they wish to display all cars.
            } 
            else if (command == 2) {
                filterCars(); // If the user enters 2, they wish to filter the cars based on condition.
            } 
            else if (command == 3) {
                purchaseCar(); // If the user enters 3, they wish to purchase a car.
            } 
            else if (command == 4) {
                viewTickets(); // If the user enters 4, they wish to view their tickets.
            } 
            else if (command == 0) {
                return; // If the user enters 0, they wish to sign out.
            } 
            else {
                System.out.println("Invalid command"); // In case the user entered an invalid command.
            }
        }
    }

    /**
     * Displays all users from the CSV file.
     */
    private static void displayAllUsers() {
        for (User user : users.values()) {
            System.out.println(user);
        }

        System.out.println("");
        System.out.println("Row content:");
        System.out.println("[First \t Last \t Balance \t Cars purchased \t Is member? \t username]");
    }

    /**
     * Displays all cars from the CSV file.
     */
    private static void displayAllCars() {
        for (Car car : cars) {
            System.out.println(car);
        }

        System.out.println("");
        System.out.println("Row content:");
        System.out.println("[ID \t Type \t Mode \t Condition \t Color \t Capacity \t Mileage \t Fuel Type \t Transmission Type \t VIN \t Price \t Cars Available]");
    }
    
    /**
     * Allows user to display new cars or used cars.
     */
    private static void filterCars() {
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
                case(1): {displayFilteredCars(true);} break; // If the user enters 1, they wish to display new cars.
                case(2): {displayFilteredCars(false);} break; // If the user enters 2, they wish to display used cars.
                case(3): return; // If the user enters 3, they wish to exit this menu.
                default: System.out.println("Invalid command"); continue; // In case the user enters an invalid command.
            }
        }
    }

    /**
     * Displays used or new cars depending on what the user desires.
     * @param printNew True if user wants to print new cars, false if user wants to print used cars.
     */
    private static void displayFilteredCars(boolean printNew) {
        // The user wishes to display new cars.
        if (printNew) {
            for (Car car : cars) {
                if (car.isNew()) {
                    System.out.println(car);
                }
            }
        }
        // The user wishes to display used cars.
        else {
            for (Car car : cars) {
                if (!car.isNew()) {
                    System.out.println(car);
                }
            }
        }
    }


    /**
     * Allows user to purchase a car.
     */
    private static void purchaseCar() {
        User currentUser = (User) currentPerson; // Cast the currentUser to a User type.
        while(true) {
            Utils.line();

            // Display the current balance of the user.
            System.out.println("Your balance is " + currentUser.getBalance());
            
            // Available options.
            System.out.println("Options:");
            System.out.println("# - Enter ID of desired car"); 
            System.out.println("0 - Go back");

            // Prompt the user for the ID of desired car.
            int command = Utils.inputOneInt("Enter ID of desired car: ");

            Utils.clear();
            
            if (command == 0) {
                return; // If the user enters 0, they wish to go back.
            }
            else if (command == -1) {
                System.out.println("Invalid command"); // In case the user enters an invalid command.
            }
            else if (command < 0 || command > cars.size()) {
                System.out.println("Invalid car ID"); // In case the user enters an invalid ID.
            }
            else {
                Car desiredCar = cars.get(command - 1); // Obtain the car the user wishes to purchase.

                // In case desired car is out of stock, inform the user.
                if(desiredCar.getVehiclesRemaining() == 0) {
                    System.out.println("Sorry,\n" + desiredCar + "\nis out of stock :(");
                    continue;
                }
                // Verify the user has sufficient funds.
                if (currentUser.getBalance() >= desiredCar.getPrice()) {

                    // Confirm the user wants to proceed with the purchase.
                    if(!confirmPurchase(desiredCar)) {
                        continue;
                    }

                    // Create a receipt since the desired vehicle is in stock, the user has sufficient funds, and the user wishes to proceed.
                    Ticket receipt = new Ticket(desiredCar.getType(), desiredCar.getModel(), 0000, desiredCar.getColor(), currentUser.getFirstName() + " " + currentUser.getLastName());
                    
                    // Add the receipt to the user's list of tickets.
                    currentUser.addTicket(receipt);

                    // Add the receipt to the shop's list of tickets.
                    allTickets.add(receipt);


                    // Update the user's balance.
                    currentUser.setBalance(Math.round((currentUser.getBalance() - desiredCar.getPrice()) * 100.0) / 100.0);

                    // Update the user's number of cars purchased.
                    currentUser.setCarsPurchased(currentUser.getCarsPurchased() + 1);

                    // Update the number of vehicles remaining for the car object.
                    desiredCar.setVehiclesRemaining(desiredCar.getVehiclesRemaining() - 1);
                    
                    // Subtract 1 from the count of cars in the CSV file.
                    decrementCarFromCSV(desiredCar.getCarID());

                    // Update the user's balance.
                    updateBalanceInCSV(currentUser);

                    // Inform the user they successfully purchased the car.
                    System.out.println("Successfully purchased:\n" + desiredCar);

                    // TODO: Add to log
                    // addToLog() - TODO by Ashkan

                }
                // Inform the user that they do not possess sufficient funds.
                else {
                    System.out.println("Sorry,\n" + desiredCar + "\ncosts $" + desiredCar.getPrice() + " but you only have $" + currentUser.getBalance());
                }
            }
        }
    }

    /**
     * Ensures the user wants to make the purchase.
     * @param desiredCar object of type Car that the user wishes to purchase.
     * @return True if the customer wishes to proceed with the purchase, False if the user changed their mind.
     */
    private static boolean confirmPurchase(Car desiredCar) {
        while (true) {
            // Available options.
            System.out.println("Are you sure you want to purchase?\n" + desiredCar);
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            // Prompt the user for input.
            int decision = Utils.inputOneInt("Enter command: ");
            Utils.clear();

            if (decision == -1) {
                System.out.println("Invalid command"); // In case the user enters an invalid command.
                continue;
            }
            else if (decision == 1) {return true;} // If the user enters 1, they wish to proceed with the purchase.
            else if (decision == 2) {return false;} // If the user enters 2, they wish to cancel the purchase.
        }
    }

    /**
     * Decrements the count of a specific vehicle in the car data CSV by 1 because it was purchased.
     * @param id The ID of the car to be decremented.
     */
    private static void decrementCarFromCSV(int id) {
        File inputFile = new File(carSourceCSV);
        File tempFile = new File("temp.csv");

        try {
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);
            writer.write(scanner.nextLine() + "\n");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int idToRemove = Integer.parseInt(parts[0]);
                if (id == idToRemove) {
                    parts[11] = "" + (cars.get(id - 1).getVehiclesRemaining());
                    line = String.join(",", parts);
                }
                writer.write(line + "\n");
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + carSourceCSV);
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }

    }

    /**
     * Updates the balance in the CSV of the user logged in because the purchased a vehicle.
     * @param user The current user logged in.
     */
    public static void updateBalanceInCSV(User user) {
        File inputFile = new File(userSourceCSV);
        File tempFile = new File("temp.csv");

        try {
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);
            writer.write(scanner.nextLine() + "\n");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[6].equals(user.getUsername())) {
                    parts[3] = "" + user.getBalance();
                    parts[4] = "" + user.getCarsPurchased();
                }
                line = String.join(",", parts);
                writer.write(line + "\n");
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + userSourceCSV);
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }
    }

    /**
     * Display tickets of the current user.
     */
    private static void viewTickets() {
        User currentUser = (User) currentPerson;
        currentUser.viewTickets();
    }

    /**
     * Display all the tickets of the shop.
     */
    private static void viewAllTickets() {
        for(Ticket ticket : allTickets) {
            System.out.println(ticket);
        }
    }

    /**
     * Initialize the Users HashMap by reading from the User Data CSV file.
     * @param sourceCSV A string to the directory of the User Data CSV file.
     */
    private static void loadUsers (String sourceCSV) {
        File f = new File(sourceCSV); // File to scan input of.
        Scanner csvLineScanner; // Scanner to scan the input.
        try {
            csvLineScanner = new Scanner(f); // Initialize the scanner with the File object.
            csvLineScanner.nextLine(); // Skip the first line.
            
            // Continue scanning while the file has lines.
            while (csvLineScanner.hasNextLine()) {
                String line = csvLineScanner.nextLine();

                Scanner cvsColScanner = new Scanner(line);
                cvsColScanner.useDelimiter(",");

                int id = cvsColScanner.nextInt();
                String first = cvsColScanner.next();
                String last = cvsColScanner.next();
                Double balance = cvsColScanner.nextDouble();
                int carsPurchased = cvsColScanner.nextInt();
                boolean isMember = cvsColScanner.nextBoolean();
                String username = cvsColScanner.next();
                String password = cvsColScanner.next();

                cvsColScanner.close();

                // Put the user in the HashMap.
                users.put(
                    username,
                    new User(id, first, last, username, password, balance, carsPurchased, isMember)
                );
            }
            csvLineScanner.close(); // Close the scanner.
        } 
        catch (FileNotFoundException e) {
            System.err.println("Users csv file " + sourceCSV + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }

    /**
     * * Initialize the Cars ArrayList by reading from the Car Data CSV file.
     * @param sourceCSV A string to the directory of the Car Data CSV file.
     */
    private static void loadCars(String sourceCSV) {
        File f = new File(sourceCSV); // File to scan the input of.
        Scanner csvCarScanner; // Scanner to scan the input.
        try {
            csvCarScanner = new Scanner(f); // Initialize the scanner with the File object.
            csvCarScanner.nextLine(); // Skip the first line.

            // Continue scanning while the file has lines.
            while (csvCarScanner.hasNextLine()) {
                String[] line = csvCarScanner.nextLine().split(",");
                // Initialize the appropriate Car object depending on the type and add to ArrayList.
                switch(line[1]) {
                    case("SUV"): {cars.add(new SUV(line)); break;}
                    case("Sedan"): {cars.add(new Sedan(line)); break;}
                    case("Pickup"): {cars.add(new Pickup(line)); break;}
                    case("Hatchback"): {cars.add(new Hatchback(line)); break;}
                }
            }
            csvCarScanner.close(); // Close the scanner.
        }
        catch(FileNotFoundException e) {
            System.err.println("Cars csv file " + sourceCSV + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }
}