package vehicles;

/**
 * Pickup class.
 */
public class Pickup extends Car {
    /**
     * 
     * @param contents string array from csv data that contains carID, type, model, isNew, color, capacity, milage,
     * fueltype, isAutomatic, VIN number, price, and the number of vehicles remaining in the shop.
     */
    public Pickup(String[] contents) {
        super(contents);
    }
}
