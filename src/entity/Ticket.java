package entity;

/**
 * Serves as proof of purchase to customer by containing relevant information such as the car type, model, year, and color.
 */
public class Ticket {
    
    /**
     * The type of the vehicle purchased.
     */
    private String type;

    /**
     * The model of the vehicle purchased.
     */
    private String model;
    
    /**
     * The year of the vehicle purchased.
     */
    private int year;

    /**
     * The color of the vehicle purchased.
     */
    private String color;
    
    /**
     * The owner who purchased the vehicle.
     */
    private String owner;

    /**
     * The price of the vehicle.
     */
    private double price;

    /**
     * The id of the vehicle.
     */
    private int id;
    
    /**
     * Get the type of the vehicle.
     * @return The type of the vehicle.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the vehicle.
     * @param type The type of the vehicle.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the model of the vehicle.
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model of the vehicle.
     * @param model The model of the vehicle.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the year of the vehicle.
     * @return The year of the vehicle.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of the vehicle.
     * @param year The year of the vehicle.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the color of the vehicle.
     * @return The color of the vehicle.
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the color of the vehicle.
     * @param color The color of the vehicle.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the owner of the vehicle.
     * @return The owner of the vehicle.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set the owner of the vehicle.
     * @param owner The owner of the vehicle.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Get the price of the vehicle.
     * @return The price of the vehicle.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Set the price of the vehicle.
     * @param price The price of the vehicle.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the ID of the vehicle.
     * @return The ID of the vehicle.
     */
    public int getID() {
        return id;
    }

    /**
     * Set the ID of the vehicle.
     * @param id The ID of the vehicle.
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /**
     * Ticket constructor that assigns type, model, year, color, and owner information.
     * @param type The type of the vehicle purchased.
     * @param model The model of the vehicle purchased. 
     * @param year The year of the vehicle purchased.
     * @param color The color of the vehicle purchased.
     * @param owner The owner who purchased the vehicle.
     * @param price The price of the vehicle.
     * @param id ID of the vehicle.
     */
    public Ticket(String type, String model, int year, String color, String owner, double price, int id) {
        this.type = type;
        this.model = model;
        this.year = year;
        this.color = color;
        this.owner = owner;
        this.price = price;
        this.id = id;
    }

    /**
     * Aesthetics for printing.
     * @return A string representation of a Ticket.
     */
    @Override
    public String toString() {
        return getType() + "\t" + getModel() + "\t" + getYear() + "\t" + getColor() + "\t" + getOwner() + "\t" + getPrice() + "\t" + getID();
    }

    /**
     * Used for printing car information.
     * @return The legend to show the order that the toString method prints cars.
     */
    public static String getLegend() {
        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[Car Type \t Car Model \t Car Year \t Car Color \t Owner \t Price \t ID]";

        return outstr;
    }

    /**
     * Compares two tickets for equality by comparing their attributes.
     * @param other the other ticket to be compared.
     * @return true if the are equal, false if they are not.
     */
    public boolean equals(Ticket other) {
        return 
        getType() == other.getType() &&
        getModel() == other.getModel() &&
        getYear() == other.getYear() &&
        getColor() == other.getColor() &&
        getOwner() == other.getOwner() &&
        getPrice() == other.getPrice() &&
        getID() == other.getID();
    }

}