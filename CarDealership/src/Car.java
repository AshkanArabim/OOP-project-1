/**
 * Car class that describes the state of a car.
 */
public class Car {
    /**
     * Car ID number used for shop identification purposes.
     */
    private int carID;

    /**
     * Model of car.
     */
    private String model;

    /**
     * Condition of car true means new, false means used.
     */
    private boolean condition;

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
    private boolean transmissionType;

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
    private boolean isAvailable;
    
    /**
     * Provides a description of a specific instance of a car.
     * @return A string description of all attributes.
     */
    @Override
    public String toString() {return "";}

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
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

    public boolean isTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(boolean transmissionType) {
        this.transmissionType = transmissionType;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
}
