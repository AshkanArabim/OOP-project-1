package vehicles;
import java.util.HashMap;

/**
 * Sedan class that is a subtype of the abstract Car class.
 */
public class Sedan extends Car {

    /**
     * Constructor that invokes the Car class through constructor chaining leveraging polymorphism.
     * @param contents Hashmap from csv data.
     */
    public Sedan(HashMap<String, String> contents) {
        super(contents);
    }

}