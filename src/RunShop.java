import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import vehicles.Car;
import vehicles.Hatchback;
import vehicles.Pickup;
import vehicles.SUV;
import vehicles.Sedan;
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

        loginScreen();
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

            // TODO: handle admin login
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
            System.out.println("5 - Sign out");

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
            } else if (command == 5) {
                // sign out
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void displayAllCars() {
        System.out.println("All cars!");
    }

    private static void filterCars() {
        System.out.println("Filter cars!");
    }

    private static void purchaseCar() {
        System.out.println("Purchase car!");
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
                
                //testing
                for (String l : line) {
                    System.out.print(l + " ");
                }

                System.out.println();
                Car car = carFactory(line);
                cars.add(car);

            }
        }
        catch(FileNotFoundException e) {
            System.err.println("Cars csv file " + sourceCSV + " not found");
            System.exit(1);
        }

    }

    private static Car carFactory(String[] contents) {
        Car c = null;
        switch(contents[1]) {
            case("Hatchback"): c = new Hatchback(Integer.valueOf(contents[0]), contents[1], 
            contents[2], Boolean.valueOf(contents[3]), contents[4], Integer.valueOf(contents[5]),
            Integer.valueOf(contents[6]), contents[7], Boolean.valueOf(contents[8]), contents[9], Double.valueOf(contents[10]),
            Integer.valueOf(contents[11])); break;

            case("Pickup"): c = new Pickup(Integer.valueOf(contents[0]), contents[1], 
            contents[2], Boolean.valueOf(contents[3]), contents[4], Integer.valueOf(contents[5]),
            Integer.valueOf(contents[6]), contents[7], Boolean.valueOf(contents[8]), contents[9], Double.valueOf(contents[10]),
            Integer.valueOf(contents[11]));

            case("SUV"): c = new SUV(Integer.valueOf(contents[0]), contents[1], 
            contents[2], Boolean.valueOf(contents[3]), contents[4], Integer.valueOf(contents[5]),
            Integer.valueOf(contents[6]), contents[7], Boolean.valueOf(contents[8]), contents[9], Double.valueOf(contents[10]),
            Integer.valueOf(contents[11]));

            case("Sedan"): c = new Sedan(Integer.valueOf(contents[0]), contents[1], 
            contents[2], Boolean.valueOf(contents[3]), contents[4], Integer.valueOf(contents[5]),
            Integer.valueOf(contents[6]), contents[7], Boolean.valueOf(contents[8]), contents[9], Double.valueOf(contents[10]),
            Integer.valueOf(contents[11]));
        }
        return c;
    }
}