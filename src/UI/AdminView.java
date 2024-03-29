package UI;

import entity.Admin;

public class AdminView {

    private Admin admin;

    // constructor
    public AdminView (Admin admin) {
        this.admin = admin;
    }

    // getters & setters
    
    // no setter because it wouldn't make sense to switch out the logged in 
    // admin in the middle of a session.
    public Admin getUser() {
        return this.admin;
    }

    // methods

    public void run() {
        Utils.clear();
        System.out.println("[ADMIN MODE]");
        System.out.println("Welcome, " + admin.getFirstName() + "!");
    }
    
}
