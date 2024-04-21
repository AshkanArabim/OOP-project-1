package datautils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import UI.Utils;
import entity.User;
import entity.Ticket;

/**
 * Handles all User-related data operations. 
 * Try to keep all User data structure modifications here (through specialized 
 * methods) to keep the UI components clean.
 */
public class UserCSVHandler extends CSVHandler {
    
    // static fields

    private static UserCSVHandler instance;

    /**
     * A string to the directory of the User Data CSV file.
     */
    private static final String CSVPATH = DATADIR + "/user_data.csv";

    /**
     * Singleton instance retreiver.
     * Will initialize the internal data structure based on users csv file on first call.
     * @return instance of UserCSVHandler
     */
    public static UserCSVHandler getInstance() {
        if (instance == null) {
            instance = new UserCSVHandler();
        }

        return instance;
    }

    // instance fields

    /**
     * Determines the user attribute order when writing to the car data CSV file.
     */
    private String[] csvCols;

    /**
     * Hashmap to efficiently match the entered username in login prompt to a valid user in the database.
     */
    private LinkedHashMap<String, User> users = new LinkedHashMap<>();

    /**
     * Private constructor to use with getInstance()
     */
    private UserCSVHandler() {
        loadUsers();
    }

    @Override
    protected void updateCSV() {
        try {
            FileWriter fw = new FileWriter(CSVPATH);

            // write csv's first line
            fw.write(String.join(",", csvCols));
            fw.write("\n");
            fw.flush();

            // write one line per user
            for (User user : users.values()) {
                fw.write(String.join(",", user.colsToAttrs(csvCols)) + "\n");
                fw.flush();
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't re-write CSV file: " + CSVPATH);
            e.printStackTrace();
            System.exit(1);
        }        
    }

    /**
     * Initialize the Users HashMap by reading from the User Data CSV file.
     * @param sourceCSV A string to the directory of the User Data CSV file.
     */
    private void loadUsers () {
        File f = new File(CSVPATH); // File to scan input of.
        Scanner csvLineScanner; // Scanner to scan the input.
        try {
            csvLineScanner = new Scanner(f); // Initialize the scanner with the File object.

            csvCols = csvLineScanner.nextLine().split(","); // save the headers
            
            // Continue scanning while the file has lines.
            while (csvLineScanner.hasNextLine()) {
                String[] line = csvLineScanner.nextLine().split(",");

                // fill user's attributes based on the headers
                HashMap<String, String> userAttrs = new HashMap<>();
                for (int i = 0; i < line.length; i++) {
                    userAttrs.put(csvCols[i], line[i]);
                }

                // use attributes dict to create a user
                User newUser = new User(userAttrs);
                users.put(newUser.getUsername(), newUser); // add to database
            }
            csvLineScanner.close(); // Close the scanner.
        } 
        catch (FileNotFoundException e) {
            System.err.println("Users csv file " + CSVPATH + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        String outstr = "";

        for (User user : users.values()) {
            outstr += user + "\n";
        }

        outstr += User.getLegend();

        return outstr;
    }

    /**
     * Iterates through all users to return a list of all tickets.
     */
    public String getAllTicketsList() {
        String outstr = "";

        for (User user: users.values()) {
            for (Ticket ticket : user.getTickets()) {
                outstr += ticket + "\n";
            }
        }

        outstr += Ticket.getLegend();

        return outstr;
    }
    
    /**
     * 
     * @param type The type of the car may be Hatchback, Sedan, SUV, or Pickup.
     * @return A description of revenue by car type.
     */
    public String getRevenueByType(String type) {
        int numberSold = 0;
        double revenue = 0;
        for (User user : users.values()) {
            for (Ticket ticket : user.getTickets()) {
                if (ticket.getType().equals(type)) {
                    revenue += ticket.getPrice();
                    numberSold++;
                }
            }
        }
        return "The shop has made $" + String.format("%.2f", revenue) + " from selling " + numberSold + " of type " + type; 
    }

    /**
     * 
     * @param id The id of the car.
     * @return A description of revenue based on the ID of the car.
     */
    public String getRevenueByID(int id) {
        
        int numberSold = 0;
        double revenue = 0;
        for (User user : users.values()) {
            for (Ticket ticket : user.getTickets()) {
                if (ticket.getID() == id) {
                    revenue += ticket.getPrice();
                    numberSold++;
                }
            }
        }
        return "The shop has made $" + String.format("%.2f", revenue) + " from selling " + numberSold + " of car ID " + id; 
    }

    /**
     * Given a username and password, checks in the database to see if there's a match.
     * @return User object if there's a match, null if no match.
     */
    public User validateAndGetUser(String username, String password) {
        User selected_user = users.getOrDefault(username, null);
        if (
            selected_user != null &&
            selected_user.getPassword().equals(password)
        ) {
            return selected_user;
        } else {
            return null;
        }
    }

    /**
     * Adds a user to CSV file.
     * @return true if successfully added, false otherwise.
     */
    public boolean addUser(String username) {
        try {
            double balance = Utils.inputOneDouble("Enter new user's balance: ");
            String password = Utils.inputOneWord("Enter new user's password: ");
            String lastName = Utils.inputOneWord("Enter new user's last name: ");
            String firstName = Utils.inputOneWord("Enter new user's first name: ");
            int id = getMaximumID() + 1;
            boolean hasMembership = Utils.inputOneLineLoop("Does the new user have a membership? [Yes|No]: ", new String[] {"Yes", "No"}).equals("Yes");
            User newUser = new User(id, firstName, lastName, username, password, balance, 0, hasMembership);
            users.put(username, newUser);
            updateCSV();
        }
        catch (IOException ioe) {
            return false;
        }
        
        return true;
    }

    /**
     * @return the maximum id of the users.
     */
    public int getMaximumID() {
        int max = Integer.MIN_VALUE;
        for (User u : users.values()) {
            if (u.getIdNumber() > max) {
                max = u.getIdNumber();
            }
        }
        return max;
    }

    /**
     * Iterates through the users to ensure we cannot add a new user with a username that already exists.
     */
    public boolean userNameExists(String name) {
        for (String username : users.keySet()) {
            if (name.equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}
