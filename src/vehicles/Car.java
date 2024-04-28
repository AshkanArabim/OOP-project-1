package vehicles;
import java.util.HashMap;
import datautils.CSVLoadable;

/**
 * Abstract car class for storing attributes and describing the state of a car.
 */
public abstract class Car implements CSVLoadable{

    /**
     * Year of the car.
     */
    private int year;

    /**
     * True if the car has a turbocharged engine.
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
     * Get year of the car.
     * @return Year of the car.
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Set year of the car.
     * @param year Year of the car.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get if the car has turbo.
     * @return If the car has turbo.
     */
    public boolean getHasTurbo() {
        return hasTurbo;
    }

    /**
     * Set if the car has turbo.
     * @param hasTurbo If the car has turbo.
     */
    public void setHasTurbo(boolean hasTurbo) {
        this.hasTurbo = hasTurbo;
    }

    /**
     * Get the ID of the car.
     * @return The ID of the car.
     */
    public int getCarID() {
        return carID;
    }
    
    /**
     * Set the ID of the car.
     * @param carID The ID of the car.
     */
    public void setCarID(int carID) {
        this.carID = carID;
    }
    
    /**
     * Get the type of the car.
     * @return The type of the car.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the car.
     * @param type The type of the car.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the model of the car.
     * @return The model of the car.
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model of the car.
     * @param model The model of the car.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get if the car is new.
     * @return If the car is new.
     */
    public boolean isNew() {
        return isNew;
    }

    /**
     * Set if the car is new.
     * @param condition If the car is new.
     */
    public void setNew(boolean condition) {
        this.isNew = condition;
    }

     /**
     * Get the color of the car.
     * @return The color of the car.
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the color of the car.
     * @param color The color of the car.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the capacity of the car.
     * @return The capacity of the car.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of the car.
     * @param capacity The capacity of the car.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get the mileage of the car.
     * @return The mileage of the car.
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Set the mileage of the car.
     * @param mileage The mileage of the car.
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Get the fuel type of the car.
     * @return The fuel type of the car.
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Set the fuel type of the car.
     * @param fuelType The fuel type of the car.
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Get if the car is automatic.
     * @return If the car is automatic.
     */
    public boolean isAutomatic() {
        return isAutomatic;
    }

    /**
     * Set if the car is automatic.
     * @param transmissionType If the car is automatic.
     */
    public void setAutomatic(boolean transmissionType) {
        this.isAutomatic = transmissionType;
    }

    /**
     * Get the VIN of the car.
     * @return The VIN of the car.
     */
    public String getVin() {
        return vin;
    }

    /**
     * Set the VIN of the car.
     * @param vin The VIN of the car.
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Get the price of the car.
     * @return The price of the car.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the car.
     * @param price The price of the car.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the number of vehicles remaining.
     * @return The number of vehicles remaining.
     */
    public int getVehiclesRemaining() {
        return vehiclesRemaining;
    }

    /**
     * Set the number of vehicles remaining.
     * @param vehiclesRemaining The number of vehicles remaining.
     */
    public void setVehiclesRemaining(int vehiclesRemaining) {
        this.vehiclesRemaining = vehiclesRemaining;
    }

    /**
     * Constructs car objects based off of data from car_data.csv.
     * @param contents Hashmap from csv data which responds dynamically to changes.
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
        this.price = Math.round(Double.parseDouble(contents.get("Price")) * 100.0) / 100.0; // Rounding to avoid precision errors.
        this.vehiclesRemaining = Integer.parseInt(contents.get("Cars Available"));
        this.year = Integer.parseInt(contents.get("Year"));
        this.hasTurbo = contents.get("hasTurbo").equals("Yes");
    }

    /**
     * Formats the attributes of Car for printing.
     * @return The legend to show the order that the toString method prints cars.
     */
    public static String getLegend() {

        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[ID \t Type \t Model \t Condition \t Color \t Capacity \t Mileage \t Fuel Type \t Transmission Type \t VIN \t Price \t Cars Available \t HasTurbo \t Year]";

        return outstr;
    }

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
     * Compares this car to another car in every field except the ID.
     * @param otherCar The other car to compare to.
     * @return True if everythng except ID matches, false otherwise.
     */
    public boolean equals(Car otherCar) {
        return (
            this.getCapacity() == otherCar.getCapacity() &&
            this.getType().equals(otherCar.getType()) &&
            this.getVehiclesRemaining() == otherCar.getVehiclesRemaining() &&
            this.isNew() == otherCar.isNew() &&
            this.getColor().equals(otherCar.getColor()) &&
            this.getYear() == otherCar.getYear() &&
            this.getPrice() == otherCar.getPrice() &&
            this.isAutomatic() == otherCar.isAutomatic() &&
            this.getVin().equals(otherCar.getVin()) &&
            this.getFuelType().equals(otherCar.getFuelType()) &&
            this.getModel().equals(otherCar.getModel()) &&
            this.getHasTurbo() == otherCar.getHasTurbo()
        );
    }

    /**
     * Initializes the columns of the CSV to the attributes.
     * @param cols The columns of the CSV.
     * @return String array of attributes.
     */
    public String[] colsToAttrs(String[] cols) {
        
        HashMap<String, String> colToAttrMap = new HashMap<>() {{
            put("ID", "" + getCarID());
            put("Car Type", getType());
            put("Model", getModel());
            put("Condition", isNew() ? "New" : "Used");
            put("Color", getColor());
            put("Capacity", "" + getCapacity());
            put("Mileage", "" + getMileage());
            put("Fuel Type", getFuelType());
            put("Transmission", isAutomatic ? "Automatic" : "Manual");
            put("VIN", getVin());
            put("Price", "" + getPrice());
            put("Cars Available", "" + getVehiclesRemaining());
            put("Year", "" + getYear());
            put("hasTurbo", getHasTurbo() ? "Yes" : "No");
        }};
        
        // Put corresponding values into attributes array.
        String[] attrs = new String[cols.length];
        for (int i = 0; i < cols.length; i++) {
            attrs[i] = colToAttrMap.get(cols[i]);
        }

        return attrs;
    }

}