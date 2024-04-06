package entity;
import UI.*;
/**
 * Serves as proof of purchase to customer by containing relevant information such as the car type, model, year, and color
 */
public class Ticket {
    private String type;
    private String model;
    private int year;
    private String color;
    private String owner;
    // constructor
    public Ticket(String type, String model, int year, String color, String owner) {
        this.type = type;
        this.model = model;
        this.year = year;
        this.color = color;
        this.owner = owner;
    }

    // methods
    @Override
    public String toString() {
        Utils.longerLine();
        return getType() + "\t" + getModel() + "\t" + getYear() + "\t" + getColor() + "\tOwner: " + getOwner();
    }

    // getters and setters

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

    
}
