package entity;

// import statements
/************************************************************************/
import java.util.ArrayList;
/************************************************************************/


/**
 * The user class is to maintain and manage data of customers.
 */
public class User extends Person {  
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
        this.balance = balance;
        this.carsPurchased = carsPurchased;
        this.isMember = isMember;
        this.tickets = new ArrayList<>();
    }
    
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
    public void viewTickets() {
        if (getTickets().isEmpty()) {
            System.out.println("You have no tickets.");
            return;
        }
        for (Ticket t : getTickets()) {
            System.out.println(t);
        }
        System.out.println("");
        System.out.println("Row content:");
        System.out.println("[Type \t Model \t Year \t Color \t Owner]");
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

    public static String getLegend() {
        String outstr = "\n";
        outstr += "Row content:\n";
        outstr += "[ID \t First Name \t Last Name \t Money Available \t Cars Purchased \t MinerCars Membership \t Username]";

        return outstr;
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
