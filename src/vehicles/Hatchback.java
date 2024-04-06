package vehicles;

/**
 * Hatchback class that is a subtype of the abstract Car class.
 */
public class Hatchback extends Car {
    /**
     * Constructor that invokes the Car class through constructor chaining leveraging polymorphism.
     * @param contents string array from csv data that contains carID, type, model, isNew, color, capacity, milage,
     * fueltype, isAutomatic, VIN number, price, and the number of vehicles remaining in the shop.
     */
    public Hatchback(String[] contents) {
        super(contents);
    }
}
