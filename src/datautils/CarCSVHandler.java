package datautils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;
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

    /**
     * A string to the directory of the Car Data CSV file.
     */
    private static final String CSVPATH = DATADIR + "/car_data.csv";
    
    /**
     * Contains Car objects from the CSV files.
     */
    private LinkedHashMap<Integer, Car> cars = new LinkedHashMap<>();

    /**
     * Singleton instance.
     */
    private static CarCSVHandler instance;

    
    /**
     * Determines the car attribute order when writing to the car data CSV file.
     */
    private String[] csvCols;
    
    /**
     * Get the HashMap of cars.
     * @return The HashMap of cars.
     */
    public LinkedHashMap<Integer, Car> getCars() {
        return cars;
    }

    /**
     * Private constructor to use with getInstance()
     */
    private CarCSVHandler() {
        loadCars();
    }

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

    /**
     * Updates the car csv file.
     */
    protected void updateCSV() {
        try {
            FileWriter fw = new FileWriter(CSVPATH);

            // Write csv's first line.
            fw.write(String.join(",", csvCols));
            fw.write("\n");
            fw.flush();

            // Write one line per car.
            for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
                Car car = entry.getValue();
                fw.write(String.join(",", car.colsToAttrs(csvCols)) + "\n");
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
     * Initialize the Cars ArrayList by reading from the Car Data CSV file.
     * @param sourceCSV A string to the directory of the Car Data CSV file.
     */
    private void loadCars() {
        File f = new File(CSVPATH); // File to scan the input of.
        Scanner csvCarScanner; // Scanner to scan the input.
        try {
            csvCarScanner = new Scanner(f); // Initialize the scanner with the File object.

            // Grab the column headers to dynamically assign attributes in ordering of CSV changes.
            csvCols = csvCarScanner.nextLine().split(",");
            CarFactory.setHeaders(csvCols);
            // Continue scanning while the file has lines.
            while (csvCarScanner.hasNextLine()) {
                String[] line = csvCarScanner.nextLine().split(",", -1);
                // Initialize the appropriate Car object using Factory Pattern depending on the type and add to ArrayList.
                Car car = CarFactory.createCar(line);
                cars.put(car.getCarID(), car);
            }
            csvCarScanner.close(); // Close the scanner.
        }
        catch(FileNotFoundException e) {
            System.err.println("Cars csv file " + CSVPATH + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }
    
    /**
     * String representation for printing.
     * @return A string representation of the car csv file.
     */
    @Override
    public String toString() {
        String outstr = "";
    
        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            outstr += entry.getValue() + "\n";
        }

        outstr += Car.getLegend();

        return outstr;
    }

    /**
     * Get a list of new cars in String form.
     */
    public String getNewCarsList() {
        String outstr = "";

        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if (car.isNew()) {
                outstr += car + "\n";
            }
        }

        outstr += Car.getLegend();

        return outstr;
    }

    /**
     * Get a list of used cars in String form.
     * @return A list of used cars as a String.
     */
    public String getUsedCarsList() {
        String outstr = "";

        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if (!car.isNew()) {
                outstr += car + "\n";
            }
        }

        outstr += Car.getLegend();

        return outstr;
    }

    /**
     * Get a car object based on the ID
     * @param id ID of the car to be found.
     * @return Car object matching ID, or null if no match.
     */
    public Car getCarByID(int id) {
        return cars.get(id);
    }

    /**
     * Get the columns of the car data csv.
     * @return The columns of the car data csv as a String array.
     */
    public String[] getCsvColumns() {
        return this.csvCols;
    }

    /**
     * Checks if the purchase is possible
     * @param id Car ID of desired car.
     * @param user User purchasing the car.
     * @return {subtotal, total} if everything goes as expected
     * @return {-1} if invalid car ID
     * @return {-2} if out of stock
     * @return {-3} if insufficient funds
     */
    public double[] validatePurchase(int id, User user) {
        Car desiredCar = getCarByID(id);
        
        if (desiredCar == null) {
            return new double[] {(double) -1};
        }

        // In case desired car is out of stock, inform the user.
        if(desiredCar.getVehiclesRemaining() == 0) {
            return new double[] {-2}; // out of stock
        }

        // Verify the user has sufficient funds.

        double subTotal = desiredCar.getPrice();
        if (user.getIsMember()) {
            // Apply discount if user is a member.
            subTotal = Math.round((subTotal - (.10 * subTotal)) * 100.0) / 100.0;
        }
        // Add taxes.
        double total = Math.round((subTotal + (.0625 * subTotal)) * 100.0) / 100.0;
        if (user.getBalance() < total) {
            return new double[] {-3}; // Insufficient funds.
        }

        return new double[] {subTotal, total};
    }

    /**
     * Purchases the case based on the ID for the user.
     * At this stage the id should be checked, and the user is assumed to have enough funds.
     * @param id ID of the Car to be purchased.
     * @param user User purchasing the car.
     * @param total The total price of the purchase.
     * @return car ID if everything goes as expected.
     * @return -1 in case of an error.
     */
    public int purchaseCar(int id, User user, double total) {
        
        // Obtain the car the user wishes to purchase. 
        // At this piont we assume the id is always correct.
        Car desiredCar = getCarByID(id); 

        // Create a ticket
        Ticket ticket = new Ticket(
            desiredCar.getType(),
            desiredCar.getModel(),
            desiredCar.getYear(),
            desiredCar.getColor(),
            user.getFirstName() + " " + user.getLastName(),
            total,
            desiredCar.getCarID()
        );
        
        // Add the ticket to the user's list of tickets.
        user.addTicket(ticket);
        
        // Update the user's number of cars purchased.
        user.setCarsPurchased(user.getCarsPurchased() + 1);
        
        // Update the number of vehicles remaining for the car object.
        desiredCar.setVehiclesRemaining(desiredCar.getVehiclesRemaining() - 1);
        this.updateCSV();

        // Update the user's balance.
        user.setBalance(Math.round((user.getBalance() - total) * 100.0) / 100.0);
        UserCSVHandler.getInstance().updateCSV();

        return desiredCar.getCarID();
    }

    /**
     * Given a list of attributes, creates and adds a car.
     * @param carAttrs Sequence of car attributes in the same order as csvCols.
     *                 Note that the ID field must be included, but it will be ignored internally.
     * @return -1 if there is a car with those exact attributes (not counting ID).
     *         Or, the ID number if the car is successfully added.
     */
    public int addCar(String[] carAttrs) {

        CarFactory.setHeaders(csvCols); // Set headers again.
        Car newCar = CarFactory.createCar(carAttrs);

        // Check if there's already a car in the database with same attributes (not considering ID).
        boolean isRepeated = false;
        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            // If any of these is true, the whole thing will become true.
            Car car = entry.getValue();
            isRepeated &= car.equals(newCar); 
            if (isRepeated) { return -1; } // Return immediately if repeated.
        }

        int newCarID = getMaximumID() + 1;
        newCar.setCarID(newCarID);
        
        cars.put(newCar.getCarID(), newCar);

        updateCSV();

        return newCarID;
    }

    /**
     * Removes a car from the ArrayList and the CAR CSV.
     * @param id The id of the car to remove.
     * @return True if the car was successfully removed, false otherwise.
     */
    public boolean removeCar(int id) {
        if (cars.containsKey(id)) {
            cars.remove(id);
            updateCSV();
            return true;
        }
        return false;
    }

    /**
     * Ensures the ID is in the arrayList of cars beforehand for tasks such as removing or obtaining revenue.
     * @param id ID of the car.
     * @return True if the cars arraylist has the ID, false otherwise.
     */
    public boolean validateID(int id) {
        return cars.containsKey(id);
    }

    /**
     * Returns the maximum id of cars for tasks like returning a car or adding a car.
     * @return Maximum car id.
     */
    public int getMaximumID() {
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if (car.getCarID() > max) {
                max = car.getCarID();
            }
        }
        return max;
    }

    /**
     * Increments the number of cars with a specified ID.
     * @param id ID of the car to be incremented.
     * @return true if operation was successful, false if not (car id not in database)
     */
    public boolean incrementCarCount(int id) {
        Car c = getCarByID(id);

        if (c != null) {
            c.setVehiclesRemaining(c.getVehiclesRemaining() + 1);
            updateCSV();
            return true;
        }

        return false;
    }

}