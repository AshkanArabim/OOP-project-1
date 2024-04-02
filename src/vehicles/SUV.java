package vehicles;

/**
 * SUV class.
 */
public class SUV extends Car {
    /**
     * 
     * @param contents string array from csv data that contains carID, type, model, isNew, color, capacity, milage,
     * fueltype, isAutomatic, VIN number, price, and the number of vehicles remaining in the shop.
     */
    public SUV(String[] contents) {
        super(contents);
    }
}
