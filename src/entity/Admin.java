package entity;

public class Admin extends Person {

    // getters & setters

    // constructor

    public Admin(
        int id, String firstName, String lastName, String username, String password
    ) {
        super(id, firstName, lastName, username, password);
    }

    // methods

    // /**
    //  * Logs in the admin.
    //  */
    // public void login() {
    //     System.out.println("Admin logging in");
    // }
}
