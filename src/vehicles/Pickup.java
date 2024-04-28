package vehicles;
import java.util.HashMap;

/**
 * Pickup class that is a subtype of the abstract Car class.
 */
public class Pickup extends Car {

    /**
     * Constructor that invokes the Car class through constructor chaining leveraging polymorphism.
     * @param contents Hashmap from csv data.
     */
    public Pickup(HashMap<String, String> contents) {
        super(contents);
    }

}