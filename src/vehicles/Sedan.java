package vehicles;

/**
 * Sedan class.
 */
public class Sedan extends Car {
    public Sedan(int carID, String type, String model, boolean isNew, String color, int capacity, int mileage, String fuelType, boolean isAutomatic, String vin, double price, int vehiclesRemaining) {
        setCarID(carID);
        setType(type);
        setModel(model);
        setNew(isNew);
        setColor(color);
        setCapacity(capacity);
        setMileage(mileage);
        setFuelType(fuelType);
        setAutomatic(isAutomatic);
        setVin(vin);
        setPrice(price);
        setVehiclesRemaining(vehiclesRemaining);
    }
}
