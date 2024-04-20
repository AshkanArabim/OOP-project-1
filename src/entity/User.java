package entity;

// import statements
/************************************************************************/
import java.util.ArrayList;
import java.util.HashMap;
/************************************************************************/

import datautils.CSVLoadable;

/**
 * The user class is to maintain and manage data of customers.
 */
public class User extends Person implements CSVLoadable{
    
    // static fields

    /**
     * Constructor that invokes abstract superclass Person through constructor chaining, leveraging the concept of polymorphism.
     * @param id Identification of the user.
     * @param firstName First name of the user.
     * @param lastName Last name of the user.
     * @param username Username of the user.
     * @param password Password of the user.
     * @param balance Balance of the user.
     * @param carsPurchased The number of cars purchased by the user.
     * @param isMember True if the user is a member, false if the user is not a member.
     */
    public User (int id, String firstName, String lastName, String username, String password,
    double balance, int carsPurchased, boolean isMember) {
        super(id, firstName, lastName, username, password);
        // cut of anything beyond the 2nd decimal point caused by precision errors
        this.balance = ((double)((int)(balance *100.0)))/100.0;
        this.carsPurchased = carsPurchased;
        this.isMember = isMember;
        this.tickets = new ArrayList<>();
    }

    /**
     * Constructor that takes a HashMap of data sourced from he user data CSV file.
     * @param contents HashMap of attributes.
     */
    public User (HashMap<String, String> contents) {

        // extract info from the hashmap, use info to call other constructor
        this(
            Integer.parseInt(contents.get("ID")),
            contents.get("First Name"),
            contents.get("Last Name"),
            contents.get("Username"),
            contents.get("Password"),
            Double.parseDouble(contents.get("Money Available")),
            Integer.parseInt(contents.get("Cars Purchased")),
            contents.get("MinerCars Membership").equals("TRUE") ? true : false
        );
    }

    /**
     * Returns the legend to show the order that the toString method prints users.
     * Try using this whenever you print any user information.
     */
    public static String getLegend() {
        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[ID \t First Name \t Last Name \t Money Available \t Cars Purchased \t MinerCars Membership \t Username]";

        return outstr;
    }

    // instance fields
    
    /**
     * Balance of the user.
     */
    private double balance;
    
    /**
     * Number of cars purchased by the user.
     */
    private int carsPurchased;
    
    /**
     * True if the user is a member, false if the user is not a member.
     */
    private boolean isMember;
    
    /**
     * A list of all the cars purchased by the user.
     */
    private ArrayList<Ticket> tickets;
    
    /**
     * Allows user to view their purchased cars.
     */
    public String getTicketsList() {
        String outstr = "";
        for (Ticket ticket : tickets) {
            outstr += ticket + "\n";
        }

        outstr += Ticket.getLegend();

        return outstr;
    }

    /**
     * Aesthetics for printing.
     */
    @Override
    public String toString() {
        return getIdNumber() + "\t" +
        getFirstName() + "\t" + 
        getLastName() + "\t" + 
        getBalance() + "\t" + 
        getCarsPurchased() + "\t" + 
        getIsMember() + "\t" + 
        getUsername() + "\t";
    }

    /**
     * Returns a sequence of corresponding attributes in the same order as given CSV columns.
     */
    public String[] colsToAttrs(String[] cols){
        
        // define column mappings
        HashMap<String, String> colToAttrMap = new HashMap<>() {{
            put("ID", "" + getIdNumber());
            put("First Name", getFirstName());
            put("Last Name", getLastName());
            put("Username", getUsername());
            put("Password", getPassword());
            put("Money Available", "" + getBalance());
            put("Cars Purchased", "" + getCarsPurchased());
            put("MinerCars Membership", getIsMember() ? "TRUE" : "FALSE");
        }};
        
        // put corresponding values into attributes array
        String[] attrs = new String[cols.length];
        for (int i = 0; i < cols.length; i++) {
            attrs[i] = colToAttrMap.get(cols[i]);
        }

        return attrs;
    }

    // getters & setters
    /************************************************************************/
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCarsPurchased() {
        return carsPurchased;
    }

    public void setCarsPurchased(int carsPurchased) {
        this.carsPurchased = carsPurchased;
    }

    public boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    public void addTicket(Ticket t) {
        this.tickets.add(t);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /************************************************************************/
}
