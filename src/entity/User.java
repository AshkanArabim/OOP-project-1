package entity;

/**
 * The user class is to maintain and manage data of customers.
 */
public class User extends Person {
    private double balance;

    private int carsPurchased;

    private boolean isMember;

    // constructor

    public User (
        int id, String firstName, String lastName, String username, String password,
        double balance, int carsPurchased, boolean isMember
    ) {
        super(id, firstName, lastName, username, password);
        this.balance = balance;
        this.carsPurchased = carsPurchased;
        this.isMember = isMember;
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

    // static methods
    public static void print(User u) {
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

    // methods

    // @Override
    // public void login() {
    //     System.out.println("Logging in");
    // }

    public void viewCars() {
    }

    public void purchaseCars() {
    }

    public void filterCars() {
    }

    public void viewTicket() {
    }
}
