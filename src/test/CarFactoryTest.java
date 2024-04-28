package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import vehicles.*;

/**
 * This class contains test cases for the CarFactory design pattern class.
 */
public class CarFactoryTest {
    /**
     * Static array to hold tokens of car attribute names.
     */
    static String[] tokens;

    /**
     * Static array to hold attribute values of car.
     */
    static String[] car;

    /**
     * Setting up tokens and car details before each test where tokens represent column headers and car represents car details.
     */
    @BeforeEach
    void setup() {
        tokens = "Capacity,Car Type,Cars Available,Condition,Color,ID,Year,Price,Transmission,VIN,Fuel Type,Model,hasTurbo".split(",");
        car = new String[] {"2", "", "3", "New", "White", "102", "2002", "5000", "Automatic", "12345", "Gasoline", "Nissan", "Yes"};
        CarFactory.setHeaders(tokens);
    }

    /**
     * Tests if carfactory creates an instance of SUV.
     */
    @Test
    void testCreateSUV() {
        // Setting car type to SUV and asserting that the created car is an instance of SUV.
        car[1] = "SUV";
        assertInstanceOf(vehicles.SUV.class, CarFactory.createCar(car));
    }

    /**
     * Tests if carfactory creates an instance of Pickup.
     */
    @Test
    void testCreatePickup() {
        // Setting car type to Pickup and asserting that the created car is an instance of Pickup.
        car[1] = "Pickup";
        assertInstanceOf(vehicles.Pickup.class, CarFactory.createCar(car));
    }

    /**
     * Tests if carfactory creates an instance of Sedan.
     */
    @Test
    void testCreateSedan() {
        // Setting car type to Sedan and asserting that the created car is an instance of Sedan.
        car[1] = "Sedan";
        assertInstanceOf(vehicles.Sedan.class, CarFactory.createCar(car));
    }

    /**
     * Tests if carfactory creates an instance of Hatchback.
     */
    @Test
    void testCreateHatchback() {
        // Setting car type to Hatchback and asserting that the created car is an instance of Hatchback.
        car[1] = "Hatchback";
        assertInstanceOf(vehicles.Hatchback.class, CarFactory.createCar(car));
    }

}