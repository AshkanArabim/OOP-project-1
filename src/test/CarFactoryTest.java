package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import vehicles.*;


public class CarFactoryTest {
    // Static arrays to hold tokens and car details
    static String[] tokens;
    static String[] car;
    
    @BeforeEach
    void setup() {
        // Setting up tokens and car details before each test
        // Tokens represent column headers, car represents car details
        tokens = "Capacity,Car Type,Cars Available,Condition,Color,ID,Year,Price,Transmission,VIN,Fuel Type,Model,hasTurbo".split(",");
        car = new String[] {"2", "", "3", "New", "White", "102", "2002", "5000", "Automatic", "12345", "Gasoline", "Nissan", "Yes"};
        CarFactory.setHeaders(tokens);
    }

    @Test
    void testCreateSUV() {
        // Setting car type to SUV and asserting that the created car is an instance of SUV
        car[1] = "SUV";
        assertInstanceOf(vehicles.SUV.class, CarFactory.createCar(car));
    }

    @Test
    void testCreatePickup() {
        // Setting car type to Pickup and asserting that the created car is an instance of Pickup
        car[1] = "Pickup";
        assertInstanceOf(vehicles.Pickup.class, CarFactory.createCar(car));
    }

    @Test
    void testCreateSedan() {
        // Setting car type to Sedan and asserting that the created car is an instance of Sedan
        car[1] = "Sedan";
        assertInstanceOf(vehicles.Sedan.class, CarFactory.createCar(car));
    }

    @Test
    void testCreateHatchback() {
        // Setting car type to Hatchback and asserting that the created car is an instance of Hatchback
        car[1] = "Hatchback";
        assertInstanceOf(vehicles.Hatchback.class, CarFactory.createCar(car));
    }

}
