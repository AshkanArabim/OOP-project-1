package vehicles;
import UI.Utils;
/**
 * Car class that describes the state of a car.
 */
public abstract class Car {

    // attributes

    /**
     * Car ID number used for shop identification purposes.
     */
    private int carID;

    /**
     * Type of car may be Sedan, Pickup, Hatchback, or SUV
     */
    private String type;
    
    /**
     * Model of car.
     */
    private String model;
    
    /**
     * Condition of car true means new, false means used.
     */
    private boolean isNew;
    
    /**
     * Color of the car.
     */
    private String color;
    
    /**
     * Refers to the number of seats.
     */
    private int capacity;
    
    /**
     * The number of miles on the car.
     */
    private int mileage;
    
    /**
     * The type of fuel the car takes, may be Hybrid, Diesel, Electric, or Gasoline.
     */
    private String fuelType;
    
    /**
     * Type of transmission is automatic if true manual otherwise.
     */
    private boolean isAutomatic;
    
    /**
     * VIN number for identification purposes.
     */
    private String vin;
    
    /**
     * Price of the car.
     */
    private double price;
    
    /**
     *  Used to determine if the vehicle is available for purchase.
     */
    private int vehiclesRemaining;
    
    
    /**
     * Provides a description of a specific instance of a car.
     * @return A string description of all attributes.
     */
    @Override
    public String toString() {
        Utils.line();
        return "ID: " + getCarID() + "\t" +
        "Car Type: " + getType() + "\t" +
        "Model: " + getModel() + "\t" + 
        "Condition: " + (isNew() ? "New" : "Used") + "\t" +
        "Color: " + getColor() + "\t" +
        "Capacity: " + getCapacity() + "\t" +
        "Mileage: " + getMileage() + "\t" + 
        "Fuel Type: " + getFuelType() + "\t" + 
        "Transmission: " + (isAutomatic() ? "Automatic" : "Manual") + "\t" +
        "VIN: " + getVin() + "\t" +
        "Price: " + getPrice() + "\t" + 
        "Cars Available: " + getVehiclesRemaining()
        ; }
    
    // getters and setters

    public int getCarID() {
        return carID;
    }
    
    public void setCarID(int carID) {
        this.carID = carID;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean condition) {
        this.isNew = condition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean transmissionType) {
        this.isAutomatic = transmissionType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVehiclesRemaining() {
        return vehiclesRemaining;
    }

    public void setVehiclesRemaining(int vehiclesRemaining) {
        this.vehiclesRemaining = vehiclesRemaining;
    }
    
}
