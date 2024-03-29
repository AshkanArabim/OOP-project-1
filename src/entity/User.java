package entity;

/**
 * The user class is to maintain and manage data of customers.
 */
public class User extends Person {

    /**
     * Username of customer.
     */
    private String userName;

    /**
     * Password of customer.
     */
    private String password;

    /**
     * Allows users to log in.
     */
    @Override
    public void login() {
        System.out.println("User logging in");
    }

    // getter & setters
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // methods

    public void viewCars() {
    }

    public void purchaseCars() {
    }

    public void filterCars() {
    }

    public void viewTicket() {
    }

    public void applyForMembership() {
    }

    public boolean isMember() {
        return true;
    }

}
