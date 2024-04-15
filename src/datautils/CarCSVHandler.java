package datautils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Ticket;
import vehicles.Car;
import vehicles.CarFactory;
import entity.User;

/**
 * Handles all the data reading and writing for cars. 
 * Try keeping all car-data-related activities here so the rest of the code stays clean.
 * UI-related methods do not print anything; they just return Strings.
 */
public class CarCSVHandler extends CSVHandler {
    
    // static fields

    /**
     * Determines the car attribute order when writing to the car data CSV file.
     */
    private static final String[] CSVORDER = {
        "Capacity",
        "Car Type",
        "Cars Available",
        "Condition",
        "Color",
        "ID",
        "Year",
        "Price",
        "Transmission",
        "VIN",
        "Fuel Type",
        "Model",
        "hasTurbo"
    };

    /**
     * A string to the directory of the Car Data CSV file.
     */
    private static final String CSVPATH = DATADIR + "/car_data.csv";
    
    private static CarCSVHandler instance;

    /**
     * Singleton instance retreiver.
     * Will initialize the internal data structure based on users csv file on first call.
     * @return instance of CarCSVHandler
     */
    public static CarCSVHandler getInstance() {
        if (instance == null) {
            instance = new CarCSVHandler();
        }

        return instance;
    }

    // instance fields

    /**
     * Contains Car objects from the CSV files.
     */
    private ArrayList<Car> cars = new ArrayList<Car>();

    /**
     * Private constructor to use with getInstance()
     */
    private CarCSVHandler() {
        loadCars();
    }

    // note: javadoc for this method provided in parent class
    protected void updateCSV() {
        try {
            FileWriter fw = new FileWriter(CSVPATH);

            // write csv's first line
            fw.write(String.join(",", CSVORDER));
            fw.write("\n");
            fw.flush();

            // write one line per car
            for (Car car : cars) {
                String line = "";
                line += 
                    car.getCapacity() + "," + 
                    car.getType() + "," + 
                    car.getVehiclesRemaining() + "," + 
                    (car.isNew() ? "New" : "Used") + "," + 
                    car.getColor() + "," + 
                    car.getCarID() + "," + 
                    car.getYear() + "," + 
                    String.format("%.2f", car.getPrice()) + "," + // price with two decimal places
                    (car.isAutomatic() ? "Automatic" : "Manual") + "," + 
                    car.getVin() + "," + 
                    car.getFuelType() + "," + 
                    car.getModel() + "," + 
                    // add the newline character at the end of line instead of a comma
                    (car.getHasTurbo() ? "Yes" : "No") + "\n";

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

    /**
     * * Initialize the Cars ArrayList by reading from the Car Data CSV file.
     * @param sourceCSV A string to the directory of the Car Data CSV file.
     */
    private void loadCars() {
        File f = new File(CSVPATH); // File to scan the input of.
        Scanner csvCarScanner; // Scanner to scan the input.
        try {
            csvCarScanner = new Scanner(f); // Initialize the scanner with the File object.

            // Grab the column headers to dynamically assign attributes in ordering of CSV changes
            String[] columnHeaders = csvCarScanner.nextLine().split(",");
            CarFactory.setHeaders(columnHeaders);
            // Continue scanning while the file has lines.
            while (csvCarScanner.hasNextLine()) {
                String[] line = csvCarScanner.nextLine().split(",", -1);
                // Initialize the appropriate Car object using Factory Pattern depending on the type and add to ArrayList.
                Car car = CarFactory.createCar(line);
                cars.add(car);
            }
            csvCarScanner.close(); // Close the scanner.
        }
        catch(FileNotFoundException e) {
            System.err.println("Cars csv file " + CSVPATH + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }
    
    @Override
    public String toString() {
        String outstr = "";
    
        for (Car car : cars) {
            outstr += car + "\n"; // car is automatically converted to String
        }

        outstr += Car.getLegend();

        return outstr;
    }

    /**
     * Get a list of new cars in String form.
     */
    public String getNewCarsList() {
        String outstr = "";

        for (Car car : cars) {
            if (car.isNew()) {
                outstr += car + "\n";
            }
        }

        outstr += Car.getLegend();

        return outstr;
    }

    /**
     * Get a list of used cars in String form.
     */
    public String getUsedCarsList() {
        String outstr = "";

        for (Car car : cars) {
            if (!car.isNew()) {
                outstr += car + "\n";
            }
        }

        outstr += Car.getLegend();

        return outstr;
    }

    public String getCarStringByID(int id) {
        return "" + cars.get(id);
    }

    /**
     * Checks if the purchase is possible
     * @return {subtotal, total} if everything goes as expected
     * @return {-1} if invalid car ID
     * @return {-2} if out of stock
     * @return {-3} if insufficient funds
     */
    public double[] validatePurchase(int id, User user) {
        if (id < 0 || id >= cars.size()) {
            return new double[] {-1}; // invalid ID
        } 
        
        Car desiredCar = cars.get(id); // Obtain the car the user wishes to purchase.

        // In case desired car is out of stock, inform the user.
        if(desiredCar.getVehiclesRemaining() == 0) {
            return new double[] {-2}; // out of stock
        }

        // Verify the user has sufficient funds.

        double subTotal = desiredCar.getPrice();
        if (user.getIsMember()) {
            // Apply discount if user is a member
            subTotal = Math.round((subTotal - (.10 * subTotal)) * 100.0) / 100.0;
        }
        // Add taxes
        double total = Math.round((subTotal + (.0625 * subTotal)) * 100.0) / 100.0;
        if (user.getBalance() < total) {
            return new double[] {-3}; // insufficient funds
        }

        return new double[] {subTotal, total};
    }

    /**
     * Purchases the case based on the ID for the user.
     * At this stage the id should be checked, and the user is assumed to have enough funds.
     * @return car ID if everything goes as expected.
     * @return -1 in case of an error.
     */
    public int purchaseCar(int id, User user, double subTotal) {
        
        // Obtain the car the user wishes to purchase. 
        // At this piont we assume the id is always correct.
        Car desiredCar = cars.get(id); 

        // Create a ticket
        Ticket ticket = new Ticket(
            desiredCar.getType(),
            desiredCar.getModel(),
            desiredCar.getYear(),
            desiredCar.getColor(),
            user.getFirstName() + " " + user.getLastName()
        );
        
        // Add the ticket to the user's list of tickets.
        user.addTicket(ticket);

        // TODO - (low priority) when we figure out a way to store and load tickets,
        //  that class will be used here to update the corresponding CSV file for tickts.
        // // Add the ticket to the shop's list of tickets.
        // allTickets.add(ticket);
        
        // Update the user's number of cars purchased.
        user.setCarsPurchased(user.getCarsPurchased() + 1);
        
        // Update the number of vehicles remaining for the car object.
        desiredCar.setVehiclesRemaining(desiredCar.getVehiclesRemaining() - 1);
        this.updateCSV();

        // Update the user's balance.
        user.setBalance(Math.round((user.getBalance() - subTotal) * 100.0) / 100.0);
        UserCSVHandler.getInstance().updateCSV();

        return desiredCar.getCarID();
    }
}
