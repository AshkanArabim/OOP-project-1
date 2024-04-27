package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import vehicles.*;

/**
 * This class contains test cases for the CarFactory class.
 */
public class CarTest {
    
    // Array to hold the keys representing column headers
    static String[] keys = "Capacity,Car Type,Cars Available,Condition,Color,ID,Year,Price,Transmission,VIN,Fuel Type,Model,hasTurbo".split(",");
    
    // Reference to another car object
    static Car otherCar;

    /**
     * Sets up resources before all tests.
     */
    @BeforeAll
    static void init() {
        // Set headers for the car factory
        CarFactory.setHeaders(keys);
    }

    /**
     * Sets up resources before each test.
     */
    @BeforeEach
    void setup() {
        // Create values for a car
        String[] values = new String[] {"2", "SUV", "3", "New", "White", "102", "2002", "5000", "Automatic", "12345", "Gasoline", "Nissan", "Yes"};
        
        // Create a car object
        otherCar = CarFactory.createCar(values);
    }

    /**
     * Tears down resources after each test.
     */
    @AfterEach
    void tearDown() {
        // Reset the reference to the other car object
        otherCar = null;
    }

    /**
     * Tests if a car created with matching SUV values is equal to the reference car.
     */
    @Test
    void testMatchingSUV() {
        // Create values for a matching SUV
        String[] values = new String[] {"2", "SUV", "3", "New", "White", "102", "2002", "5000", "Automatic", "12345", "Gasoline", "Nissan", "Yes"};
        
        // Assert that a car created with the same values as otherCar is equal to otherCar
        assertTrue(CarFactory.createCar(values).equals(otherCar));
    }

    /**
     * Tests if a car created with non-matching SUV values is not equal to the reference car.
     */
    @Test
    void testNonMatchingSUV() {
        // Create values for a non-matching SUV
        String[] values = new String[] {"2", "Sedan", "3", "New", "White", "102", "2002", "5000", "Automatic", "12345", "Gasoline", "Nissan", "Yes"};
        
        // Assert that a car created with the non-matching values is not equal to otherCar
        assertFalse(CarFactory.createCar(values).equals(otherCar));
    }

    /**
     * Tests if a car created with matching Sedan values is equal to the reference car.
     */
    @Test
    void testMatchingSedan() {
        // Create values for a matching Sedan
        String[] values = new String[] {"2", "Sedan", "3", "New", "White", "102", "2002", "5000", "Automatic", "12345", "Gasoline", "Nissan", "Yes"};
        
        // Create another car object with matching values
        otherCar = CarFactory.createCar(values);
        
        // Assert that this newly created car is equal to otherCar
        assertTrue(CarFactory.createCar(values).equals(otherCar));
    }
}
