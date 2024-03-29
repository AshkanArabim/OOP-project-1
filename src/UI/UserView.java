package UI;

import entity.User;
import UI.Utils;

public class UserView {

    private User user;

    // constructor
    public UserView (User user) {
        this.user = user;
    }

    // getters & setters
    
    // no setter because it wouldn't make sense to switch out the logged in 
    // user in the middle of a session.
    public User getUser() {
        return this.user;
    }

    // methods

    public void run() {
        Utils.clear();
        System.out.println("Welcome, " + user.getFirstName() + "!");
    }
    
}
