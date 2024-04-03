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

/**
 * This is the main runner class.
 * @author Ashkan Arabi
 * @author James Newson
 * @author Ruben Martinez
 * @version Part 1
 */

public class RunShop {

    // global vars

    private static ArrayList<Car> cars = new ArrayList<Car>();

    private static Person currentPerson;

    // Using a hashmap to quickly match the entered username (in login prompt)
    // to a valid user in the database. Awesome efficiency.
    private static HashMap<String, User> users = new HashMap<String, User>();

    /**
     * Main method.
     * 
     * @param args
     */    
    public static void main(String[] args) {

        loadUsers("../data/user_data.csv");
        loadCars("../data/car_data.csv");

        // loginScreen();
        userLogin();
    }

    // helper methods

    private static void loginScreen() {
        Utils.clear();

        // loop so it prompts again when user signs out.
        while (true) {
            Utils.line();
            System.out.println("Welcome to Mine Cars!");
            System.out.println();
    
            // username and password prompts
                // calls appropriate interface (user / admin)
            
            String username = Utils.inputOneWord("Username: ");
            String password = Utils.inputOneWord("Password: ");
            
            // clearing before error messages so user has a chance to see them
            Utils.clear();

            // Handle admin login
            // For now I'm assuming the admin will use "admin" and "admin" as 
            // both the username and password.
            // 
            // Also assuming the adming has the following attributes:
            // - Firstname: Admin
            // - Lastname: Adminson
            if (username.equals("admin") && password.equals("admin")) {
                currentPerson = new Admin(0, "Admin", "Adminson", username, password);
                adminLogin();
                currentPerson = null;
                continue;
            }
            
            User selected_user = users.getOrDefault(username, null);
            if (
                selected_user != null &&
                selected_user.getPassword().equals(password)
            ) {
                // if username + password match
                currentPerson = selected_user;
                userLogin();
                currentPerson = null; 
                System.out.println("Logged out.");
            } else {
                // if they don't match
                System.out.println("Username or password incorrect.");
            }
        }
    }

    private static void adminLogin() {
        while (true) {
            // print available options
            Utils.line();
            System.out.println("[ADMIN MODE]");
            System.out.println("Options:");
            System.out.println("1 - Display all users");
            System.out.println("0 - Sign out");

            // get input
            int command = Utils.inputOneInt("Enter command: ");

            Utils.clear();

            if (command == 1) {
                displayAllUsers();
            } else if (command == 0) {
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void userLogin() {
        while (true) {
            // print available options
            Utils.line();
            System.out.println("Options:");
            System.out.println("1 - Display all cars");
            System.out.println("2 - Filter Cars (used / new)");
            System.out.println("3 - Purchase a car");
            System.out.println("4 - View Tickets");
            System.out.println("0 - Sign out");

            // get input
            int command = Utils.inputOneInt("Enter command: ");

            Utils.clear();

            if (command == 1) {
                displayAllCars();
            } else if (command == 2) {
                filterCars();
            } else if (command == 3) {
                purchaseCar();
            } else if (command == 4) {
                viewTickets();
            } else if (command == 0) {
                // sign out
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void displayAllUsers() {
        for (User user : users.values()) {
            User.print(user);
        }

        System.out.println("");
        System.out.println("Row content:");
        System.out.println("[First \t Last \t Balance \t Cars purchased \t Is member? \t username]");
    }

    private static void displayAllCars() {
        for (Car car : cars) {
            System.out.println(car);
        }

        System.out.println("");
        System.out.println("Row content:");
        System.out.println("[ID \t Type \t Mode \t Condition \t Color \t Capacity \t Mileage \t Fuel Type \t Transmission Type \t VIN \t Price \t Cars Available]");
    }

    private static void displayUsedCars() {
        for (Car car : cars) {
            if (!car.isNew()) {
                System.out.println(car);
            }
        }
    }

    private static void displayNewCars() {
        for (Car car : cars) {
            if (car.isNew()) {
                System.out.println(car);
            }
        }
    }

    private static void filterCars() {
        while(true) {
            Utils.line();
            System.out.println("Options:");
            System.out.println("1 - Display New Cars");
            System.out.println("2 - Display Used Cars");
            System.out.println("3 - Go back");

            // get input
            int command = Utils.inputOneInt("Enter command: ");

            Utils.clear();

            switch(command) {
                case(1): {displayNewCars();}; break;
                case(2): {displayUsedCars();} break;
                case(3): return;
                // command returned -1 meaning the user entered something other than an int
                default: System.out.println("Invalid command"); continue;
            }
        }
    }

    private static void purchaseCar() {

        while(true) {
            Utils.line();

            // testing purposes
            User currentUser = users.get("batman");
            System.out.println("Your balance is " + currentUser.getBalance());
            
            System.out.println("Options:");
            System.out.println("# - Enter ID of desired car");
            System.out.println("0 - Go back");

            // get input
            int command = Utils.inputOneInt("Enter ID of desired car: ");

            Utils.clear();
            
            // Go back
            if (command == 0) {
                return;
            }
            // User did not enter an int
            else if (command == -1) {
                System.out.println("Invalid command");
            }
            // User entered an ID that was out of bounds
            else if (command < 0 || command > cars.size()) {
                System.out.println("Invalid car ID");
            }
            else {
                Car desiredCar = cars.get(command - 1);
                if(desiredCar.getVehiclesRemaining() == 0) {
                    System.out.println("Sorry,\n" + desiredCar + "\nis out of stock :(");
                    continue;
                }
                // Verify the user has sufficient funds
                if (currentUser.getBalance() >= desiredCar.getPrice()) {
                    // method that confirms if the user wants to follow through with the purchase.
                    if(!confirmPurchase(desiredCar)) {
                        continue;
                    }
                    currentUser.setBalance(currentUser.getBalance() - desiredCar.getPrice());
                    currentUser.setCarsPurchased(currentUser.getCarsPurchased() + 1);
                    // decrement count of vehicle
                    desiredCar.setVehiclesRemaining(desiredCar.getVehiclesRemaining() - 1);
                    
                    // in case the user purchases the last vehicle
                    if (desiredCar.getVehiclesRemaining() == 0) {
                        // remove from CSV.
                        removeCarFromCSV("../data/car_data.csv", desiredCar.getCarID());
                    }
                    else {
                        // decrement from csv
                        decrementCarFromCSV("../data/car_data.csv", desiredCar.getCarID());
                    }
                    System.out.println("Succesfully purchased:\n" + desiredCar);

                    // TODO: Add to log
                    // addToLog() - TODO by Ashkan
                    // updateUserBalanceinCSV - TODO by Ashkan
                }
                else {
                    System.out.println("Sorry,\n" + desiredCar + "\ncosts $" + desiredCar.getPrice() + " but you only have $" + currentUser.getBalance());
                }
            }

        }

    }

    private static boolean confirmPurchase(Car desiredCar) {
        while (true) {
            System.out.println("Are you sure you want to purchase?\n" + desiredCar);
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            int decision = Utils.inputOneInt("Enter command: ");
            Utils.clear();
            if (decision == -1) {
                System.out.println("Invalid command");
                continue;
            }
            else if (decision == 1) {return true;}
            else {return false;}
        }
    }

    private static void removeCarFromCSV(String sourceCSV, int id) {
        File inputFile = new File(sourceCSV);
        File tempFile = new File("temp.csv");

        try {
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);
            writer.write(scanner.nextLine() + "\n");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int idToRemove = Integer.parseInt(parts[0]);
                if (id != idToRemove) {
                    writer.write(line + "\n");
                }
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + sourceCSV);
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }
    }

    private static void decrementCarFromCSV(String sourceCSV, int id) {
        File inputFile = new File(sourceCSV);
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
            System.err.println("File not found: " + sourceCSV);
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }
    }

    private static void viewTickets() {
        System.out.println("View tickets!");
    }

    private static void loadUsers (String sourceCSV) {
        File f = new File(sourceCSV);
        
        Scanner csvLineScanner;
        try {
            csvLineScanner = new Scanner(f);
            boolean firstRowSkipped = false;
            
            while (csvLineScanner.hasNextLine()) {
                String line = csvLineScanner.nextLine();
                
                if (!firstRowSkipped) {
                    firstRowSkipped = true;
                    continue;
                }

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

                // users.add(
                //     new User(id, first, last, username, password, balance, carsPurchased, isMember)
                // );
                users.put(
                    username,
                    new User(id, first, last, username, password, balance, carsPurchased, isMember)
                );
            }

            csvLineScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Users csv file " + sourceCSV + " not found");
            System.exit(1);
        }
    }

    private static void loadCars(String sourceCSV) {
        File f = new File(sourceCSV);
        Scanner csvCarScanner;
        try {
            csvCarScanner = new Scanner(f);
            // skip the first line
            csvCarScanner.nextLine();

            while (csvCarScanner.hasNextLine()) {
                String[] line = csvCarScanner.nextLine().split(",");
                Car car = null;
                switch(line[1]) {
                    case("SUV"): {car = new SUV(line); break;}
                    case("Sedan"): {car = new Sedan(line); break;}
                    case("Pickup"): {car = new Pickup(line); break;}
                    case("Hatchback"): {car = new Hatchback(line); break;}
                }
                cars.add(car);
            }
        }
        catch(FileNotFoundException e) {
            System.err.println("Cars csv file " + sourceCSV + " not found");
            System.exit(1);
        }
        // displayAllCars();
    }
}