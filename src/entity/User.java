package entity;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The user class is to maintain and manage data of customers.
 */
public class User extends Person {
    private double balance;

    private int carsPurchased;

    private boolean isMember;

    private ArrayList<Ticket> tickets;
    // constructor

    public User (
        int id, String firstName, String lastName, String username, String password,
        double balance, int carsPurchased, boolean isMember
    ) {
        super(id, firstName, lastName, username, password);
        this.balance = balance;
        this.carsPurchased = carsPurchased;
        this.isMember = isMember;
        this.tickets = new ArrayList<>();
    }

    public void updateBalanceInCSV(String sourceCSV) {
        File inputFile = new File(sourceCSV);
        File tempFile = new File("temp.csv");

        try {
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);
            writer.write(scanner.nextLine() + "\n");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[6].equals(getUsername())) {
                    parts[3] = "" + getBalance();
                    parts[4] = "" + getCarsPurchased();
                }
                line = String.join(",", parts);
                writer.write(line + "\n");
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + sourceCSV);
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }
    }

    // static methods
    public static void printUser(User u) {
        System.out.println(
            "" + u.getIdNumber() + "\t" +
            u.getFirstName() + "\t" + 
            u.getLastName() + "\t" + 
            u.getBalance() + "\t" + 
            u.getCarsPurchased() + "\t" + 
            u.getIsMember() + "\t" + 
            u.getUsername() + "\t"
        );
    }

    // getters & setters

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

    public void viewTickets() {
        for (Ticket t : getTickets()) {
            System.out.println(t);
        }
    }
}
