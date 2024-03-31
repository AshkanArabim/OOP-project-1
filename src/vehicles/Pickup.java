package vehicles;

/**
 * Pickup class.
 */
public class Pickup extends Car {
    public Pickup(int carID, String type, String model, boolean isNew, String color, int capacity, int mileage, String fuelType, boolean isAutomatic, String vin, double price, int vehiclesRemaining) {
        super(carID, type, model, isNew, color, capacity, mileage, fuelType, isAutomatic, vin, price, vehiclesRemaining);
    }
}
