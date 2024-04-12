package vehicles;
import java.util.HashMap;
/**
 * SUV class that is a subtype of the abstract Car class.
 */
public class SUV extends Car {
    /**
     * Constructor that invokes the Car class through constructor chaining leveraging polymorphism.
     * @param contents hashmap from csv data
     */
    public SUV(HashMap<String, String> contents) {
        super(contents);
    }
}
