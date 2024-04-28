package datautils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import UI.Utils;
import entity.User;
import entity.Ticket;

/**
 * Singleton class that handles all User-related data operations. 
 */
public class UserCSVHandler extends CSVHandler {
    
    /**
     * Singleton instance.
     */
    private static UserCSVHandler instance;

    /**
     * A string to the directory of the User Data CSV file.
     */
    private static final String CSVPATH = DATADIR + "/user_data.csv";

    /**
     * Determines the user attribute order when writing to the car data CSV file.
     */
    private String[] csvCols;

    /**
     * Hashmap to efficiently match the entered username in login prompt to a valid user in the database.
     */
    private LinkedHashMap<String, User> users = new LinkedHashMap<>();
    
    /**
     * Singleton instance retreiver.
     * Will initialize the internal data structure based on users csv file on first call.
     * @return Instance of UserCSVHandler.
     */
    public static UserCSVHandler getInstance() {
        if (instance == null) {
            instance = new UserCSVHandler();
        }

        return instance;
    }

    /**
     * Private constructor to use with getInstance()
     */
    private UserCSVHandler() {
        loadUsers();
    }

    /**
     * Updates the user csv file.
     */
    @Override
    protected void updateCSV() {
        try {
            FileWriter fw = new FileWriter(CSVPATH);

            // Write csv's first line.
            fw.write(String.join(",", csvCols));
            fw.write("\n");
            fw.flush();

            // Write one line per user.
            for (User user : users.values()) {
                fw.write(String.join(",", user.colsToAttrs(csvCols)) + "\n");
                fw.flush();
            }

            fw.close();
        } 
        catch (Exception e) {
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

            csvCols = csvLineScanner.nextLine().split(","); // Save the headers.
            
            // Continue scanning while the file has lines.
            while (csvLineScanner.hasNextLine()) {
                String[] line = csvLineScanner.nextLine().split(",");

                // Fill user's attributes based on the headers.
                HashMap<String, String> userAttrs = new HashMap<>();
                for (int i = 0; i < line.length; i++) {
                    userAttrs.put(csvCols[i], line[i]);
                }

                // Use attributes dict to create a user.
                User newUser = new User(userAttrs);
                users.put(newUser.getUsername(), newUser); // Add to database.
            }
            csvLineScanner.close(); // Close the scanner.
        } 
        catch (FileNotFoundException e) {
            System.err.println("Users csv file " + CSVPATH + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }

    /**
     * String representation for printing.
     * @return A string representation of the user csv file.
     */
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
     * @return A list of all tickets as a String.
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
     * Get the revenue by type.
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
     * Get the revenue by the ID,
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
     * @param username The username of the user.
     * @param password The password of the user.
     * @return User object if there's a match, null if no match.
     */
    public User validateAndGetUser(String username, String password) {
        User selected_user = users.getOrDefault(username, null);
        if (
            selected_user != null &&
            selected_user.getPassword().equals(password)
        ) {
            return selected_user;
        } 
        else {
            return null;
        }
    }

    /**
     * Adds a user to CSV file.
     * @return True if successfully added, false otherwise.
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
     * Find the maximum ID of the users.
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
     * @return True if the user already exists, false otherwise.
     */
    public boolean userNameExists(String name) {
        for (String username : users.keySet()) {
            if (name.equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Allows users to return cars.
     * @param username The username of the user.
     * @param id The ID of the car desired to return.
     * @return True if the car is successfully returned, false otherwise.
     */
    public boolean returnCar(String username, int id) {
        User selected_user = users.getOrDefault(username, null);
         
        if (CarCSVHandler.getInstance().getCarByID(id) == null) {
            System.out.println("Could not return car.");
            return false;
        }

        Ticket reciept = null;
        for (Ticket t : selected_user.getTickets()) {
            if (t.getID() == id) {
                reciept = t;
                break;
            }
        }

        if (reciept == null) {
            System.out.println("You do not own car " + id);
            return false;
        }
        else {
            selected_user.setBalance(Math.round((selected_user.getBalance() + reciept.getPrice()) * 100.0) / 100.0);
            selected_user.getTickets().remove(reciept);
            selected_user.setCarsPurchased(selected_user.getCarsPurchased() - 1);
            updateCSV();
        }

        return true;
    }

}