package entity;
import java.util.ArrayList;
import java.util.HashMap;
import datautils.CSVLoadable;

/**
 * The user class is to maintain and manage data of customers.
 */
public class User extends Person implements CSVLoadable{

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
     * Get the balance of the user.
     * @return The balance of the user.
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Set the balance of the user.
     * @param balance The balance of the user.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Get the number of cars purchased by the user.
     * @return The number of cars purchased by the user.
     */
    public int getCarsPurchased() {
        return carsPurchased;
    }

    /**
     * Set the number of cars purchased by the user.
     * @param carsPurchased The number of cars purchased by the user.
     */
    public void setCarsPurchased(int carsPurchased) {
        this.carsPurchased = carsPurchased;
    }

    /**
     * Get if the user is a member.
     * @return True if the user is a member, false otherwise.
     */
    public boolean getIsMember() {
        return isMember;
    }

    /**
     * Set if the user is a member.
     * @param isMember True if the user is a member, false otherwise.
     */
    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    /**
     * Add a ticket to the tickets when a user purchases a car.
     * @param t The ticket to be added.
     */
    public void addTicket(Ticket t) {
        this.tickets.add(t);
    }

    /**
     * List of the tickets containing information about the cars the user has purchased.
     * @return The list of tickets.
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

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
        this.balance = Math.round(balance * 100.0) / 100.0; // Avoid precision errors.
        this.carsPurchased = carsPurchased;
        this.isMember = isMember;
        this.tickets = new ArrayList<>();
    }

    /**
     * Constructor that takes a HashMap of data sourced from he user data CSV file.
     * @param contents HashMap of attributes.
     */
    public User (HashMap<String, String> contents) {

        // Extract info from the hashmap, use info to call other constructor.
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
     * Legend for whenever printing any user information.
     * @return The legend to show the order that the toString method prints users.
     */
    public static String getLegend() {

        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[ID \t First Name \t Last Name \t Money Available \t Cars Purchased \t MinerCars Membership \t Username]";

        return outstr;
    }
    
    /**
     * Allows user to view their purchased cars.
     * @return String of owned cars.
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
     * @return A string for printing.
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
     * Defines the column mappings and initializes attributes array.
     * @return A sequence of corresponding attributes in the same order as given CSV columns.
     */
    public String[] colsToAttrs(String[] cols){
        
        // Define column mappings.
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
        
        // Put corresponding values into attributes array.
        String[] attrs = new String[cols.length];
        for (int i = 0; i < cols.length; i++) {
            attrs[i] = colToAttrMap.get(cols[i]);
        }

        return attrs;
    }

}