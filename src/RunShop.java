import vehicles.*;
import entity.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the main runner class.
 * @author Ashkan Arabi
 * @author James Newson
 * @author Ruben Martinez
 * @version Part One
 */

public class RunShop {
    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        scanCarData();
    }
    
    /**
     * Method that scans the car data csv file and initalizes objects.
     * @return Scanner reference object whose cursor is at the beginning of the file.
     */
    public static Scanner scanCarData() {
        Scanner carDataScanner = null;
        try {
            carDataScanner = new Scanner(new File("data/car_data.csv"));
            System.out.println(carDataScanner.nextLine());

        }
        catch (FileNotFoundException f) {
            System.out.println("File not found.");
        }
        return carDataScanner;
    }
}