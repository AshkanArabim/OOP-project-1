package vehicles;

import java.util.HashMap;

/**
 * Car class that is abstract and describes the state of a car.
 */
public abstract class Car {
    /**
     * Constructs car objects based off of data from car_data.csv.
     * @param contents hashmap from csv data. Responds dynamically to changes.
     */
    public Car(HashMap<String, String> contents) {
        this.carID = Integer.parseInt(contents.get("ID"));
        this.type = contents.get("Car Type");
        this.model = contents.get("Model");
        this.isNew = contents.get("Condition").equals("New") ? true : false;
        this.color = contents.get("Color");
        this.capacity = Integer.parseInt(contents.get("Capacity"));
        this.mileage = Integer.parseInt(contents.getOrDefault("Mileage", "0"));
        this.fuelType = contents.get("Fuel Type");
        this.isAutomatic = contents.get("Transmission").equals("Automatic") ? true : false;
        this.vin = contents.get("VIN");
        this.price = Double.parseDouble(contents.get("Price"));
        this.vehiclesRemaining = Integer.parseInt(contents.get("Cars Available"));
        this.year = Integer.parseInt(contents.get("Year"));
        this.hasTurbo = contents.get("hasTurbo").equals("Yes");
    }

    /**
     * Year of the car.
     */
    private int year;

    /**
     * If the car has a turbocharged engine.
     */
    private boolean hasTurbo;

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
     * True means new, false means used.
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
        return 
        getCarID() + "\t" + 
        getType() + "\t" + 
        getModel() + "\t" + 
        (isNew() ? "New" : "Used") + "\t" + 
        getColor() + "\t" + 
        getCapacity() + "\t" + 
        getMileage() + "\t" + 
        getFuelType() + "\t" + 
        (isAutomatic() ? "Automatic" : "Manual") + "\t" +
        getVin() + "\t" + 
        getPrice() + "\t" + 
        getVehiclesRemaining() + "\t" +
        getHasTurbo() + "\t" +
        getYear();
    }

    /**
     * Returns the legend to show the order that the toString method prints cars.
     * Try using this whenever you print any car information.
     */
    public static String getLegend() {
        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[ID \t Type \t Model \t Condition \t Color \t Capacity \t Mileage \t Fuel Type \t Transmission Type \t VIN \t Price \t Cars Available \t HasTurbo \t Year]";

        return outstr;
    }
    
    // getters and setters
    /************************************************************************/
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getHasTurbo() {
        return hasTurbo;
    }

    public void setHasTurbo(boolean hasTurbo) {
        this.hasTurbo = hasTurbo;
    }

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
    /************************************************************************/
}
