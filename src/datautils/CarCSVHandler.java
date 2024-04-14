package datautils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import vehicles.Car;
import vehicles.CarFactory;

public class CarCSVHandler extends CSVHandler {

    // static fields

    private static CarCSVHandler instance;

    /**
     * A string to the directory of the Car Data CSV file.
     */
    private static String carSourceCSV = DATADIR + "/car_data.csv";

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
    private static ArrayList<Car> cars = new ArrayList<Car>();

    /**
     * Private constructor to use with getInstance()
     */
    private CarCSVHandler() {
        loadCars();
    }

    /**
     * Updates the CSV file holder user data.
     * Call this in every method of this class that modifies the "users" data structure
     */
    protected void updateCSV() {
        // TODO:
    }

    // /**
    //  * Decrements the count of a specific vehicle in the car data CSV by 1 because it was purchased.
    //  * @param id The ID of the car to be decremented.
    //  */
    // // FIXME: move this to the car csv handler class
    // private static void decrementCarFromCSV(int id) {
    //     File inputFile = new File(carSourceCSV);
    //     File tempFile = new File("temp.csv");

    //     try {
    //         Scanner scanner = new Scanner(inputFile);
    //         FileWriter writer = new FileWriter(tempFile);
    //         writer.write(scanner.nextLine() + "\n");
    //         while (scanner.hasNextLine()) {
    //             String line = scanner.nextLine();
    //             String[] parts = line.split(",", -1);
    //             int idToRemove = Integer.parseInt(parts[CarFactory.columnMapIndices.get("ID")]);
    //             if (id == idToRemove) {
    //                 parts[CarFactory.columnMapIndices.get("Cars Available")] = "" + (cars.get(id - 1).getVehiclesRemaining());
    //                 line = String.join(",", parts);
    //             }
    //             writer.write(line + "\n");
    //         }

    //         scanner.close();
    //         writer.close();
    //     } catch (FileNotFoundException e) {
    //         System.err.println("File not found: " + carSourceCSV);
    //     } catch (IOException e) {
    //         System.err.println("Error reading or writing file: " + e.getMessage());
    //     }

    //     // Replace the original file with the temporary file
    //     if (!tempFile.renameTo(inputFile)) {
    //         System.err.println("Could not rename temporary file");
    //     }

    // }

    /**
     * * Initialize the Cars ArrayList by reading from the Car Data CSV file.
     * @param sourceCSV A string to the directory of the Car Data CSV file.
     */
    // FIXME: add to car csv handler
    private void loadCars() {
        File f = new File(carSourceCSV); // File to scan the input of.
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
            System.err.println("Cars csv file " + carSourceCSV + " not found"); // In case the file could not be located.
            System.exit(1);
        }
    }
    
}
