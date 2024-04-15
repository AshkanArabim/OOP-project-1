package datautils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Scanner;

import entity.User;

public class UserCSVHandler extends CSVHandler {
    
    // static fields

    /**
     * Determines the user attribute order when writing to the car data CSV file.
     */
    private static final String[] CSVORDER = {
        "ID",
        "First Name",
        "Last Name",
        "Money Available",
        "Cars Purchased",
        "MinerCars Membership",
        "Username",
        "Password"
    };

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
     * Hashmap to efficiently match the entered username in login prompt to a valid user in the database.
     */
    private LinkedHashMap<String, User> users = new LinkedHashMap<>();

    /**
     * Private constructor to use with getInstance()
     */
    private UserCSVHandler() {
        loadUsers();
    }

    // note: javadoc for this method provided in parent class
    @Override
    protected void updateCSV() {
        try {
            FileWriter fw = new FileWriter(CSVPATH);

            // write csv's first line
            fw.write(String.join(",", CSVORDER));
            fw.write("\n");
            fw.flush();

            // write one line per user
            for (User user : users.values()) {
                String line = "";
                line += 
                    user.getIdNumber() + "," +
                    user.getFirstName() + "," +
                    user.getLastName() + "," +
                    String.format("%.2f", user.getBalance()) + "," +
                    user.getCarsPurchased() + "," +
                    (user.getIsMember() ? "True" : "False") + "," +
                    user.getUsername() + "," +
                    // add the newline character at the end of line instead of a comma
                    user.getPassword() + "\n";

                fw.write(line);
                fw.flush();
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't re-write CSV file: " + CSVPATH);
            e.printStackTrace();
            System.exit(1);
        }        
    }

    // /**
    //  * Updates the balance in the CSV of the user logged in because the purchased a vehicle.
    //  * @param user The current user logged in.
    //  */
    // // FIXME: move to user csv handler class
    // public void updateBalanceInCSV(User user) {
    //     File inputFile = new File(userSourceCSV);
    //     File tempFile = new File("temp.csv");

    //     try {
    //         Scanner scanner = new Scanner(inputFile);
    //         FileWriter writer = new FileWriter(tempFile);
    //         writer.write(scanner.nextLine() + "\n");
    //         while (scanner.hasNextLine()) {
    //             String line = scanner.nextLine();
    //             String[] parts = line.split(",");
    //             if (parts[6].equals(user.getUsername())) {
    //                 parts[3] = "" + user.getBalance();
    //                 parts[4] = "" + user.getCarsPurchased();
    //             }
    //             line = String.join(",", parts);
    //             writer.write(line + "\n");
    //         }

    //         scanner.close();
    //         writer.close();
    //     } catch (FileNotFoundException e) {
    //         System.err.println("File not found: " + userSourceCSV);
    //     } catch (IOException e) {
    //         System.err.println("Error reading or writing file: " + e.getMessage());
    //     }

    //     // Replace the original file with the temporary file
    //     if (!tempFile.renameTo(inputFile)) {
    //         System.err.println("Could not rename temporary file");
    //     }
    // }

    /**
     * Initialize the Users HashMap by reading from the User Data CSV file.
     * @param sourceCSV A string to the directory of the User Data CSV file.
     */
    private void loadUsers () {
        File f = new File(CSVPATH); // File to scan input of.
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
}
