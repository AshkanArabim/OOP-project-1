package entity;
/**
 * Serves as proof of purchase to customer by containing relevant information such as the car type, model, year, and color
 */
public class Ticket {
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
     * Aesthetics for printing.
     */
    @Override
    public String toString() {
        return getType() + "\t" + getModel() + "\t" + getYear() + "\t" + getColor() + "\t" + getOwner() + "\t" + getPrice() + "\t" + getID();
    }

    /**
     * Returns the legend to show the order that the toString method prints cars.
     * Try using this whenever you print any car information.
     */
    public static String getLegend() {
        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[Car Type \t Car Model \t Car Year \t Car Color \t Owner \t Price \t ID]";

        return outstr;
    }

    // getters and setters
    /************************************************************************/
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
    /************************************************************************/
}
