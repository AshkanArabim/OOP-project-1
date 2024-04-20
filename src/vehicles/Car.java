package vehicles;

import java.util.HashMap;

import datautils.CSVLoadable;

/**
 * Car class that is abstract and describes the state of a car.
 */
public abstract class Car implements CSVLoadable{

    // static fields

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
        // cut of anything beyond the 2nd decimal point caused by precision errors
        this.price = ((double)((int)(
            Double.parseDouble(contents.get("Price")) * 100.0
        )))/100.0;
        this.vehiclesRemaining = Integer.parseInt(contents.get("Cars Available"));
        this.year = Integer.parseInt(contents.get("Year"));
        this.hasTurbo = contents.get("hasTurbo").equals("Yes");
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

    // instance fields

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
     * Compares this car to another car in every field except the ID.
     * @return true if everythng except ID matches, false otherwise.
     */
    @Override
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
     * Returns a sequence of corresponding attributes in the same order as given CSV columns.
     */
    public String[] colsToAttrs(String[] cols) {
        
        // define column mappings
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
        
        // put corresponding values into attributes array
        String[] attrs = new String[cols.length];
        for (int i = 0; i < cols.length; i++) {
            attrs[i] = colToAttrMap.get(cols[i]);
        }

        return attrs;
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
