package vehicles;
import java.util.HashMap;
/**
 * Hatchback class that is a subtype of the abstract Car class.
 */
public class Hatchback extends Car {
    /**
     * Constructor that invokes the Car class through constructor chaining leveraging polymorphism.
     * @param contents hashmap from csv data
     */
    public Hatchback(HashMap<String, String> contents) {
        super(contents);
    }
}
