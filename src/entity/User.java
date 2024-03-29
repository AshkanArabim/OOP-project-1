package entity;

public class User extends Person {

    private double balance;

    private int carsPurchased;

    private boolean isMember;

    // getters & setters

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCarsPurchased() {
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

    // methods

    @Override
    public void login() {
        System.out.println("Logging in");
    }

    public void viewCars() {
    }

    public void purchaseCars() {
    }

    public void filterCars() {
    }

    public void viewTicket() {
    }
}
