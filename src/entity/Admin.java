package entity;

/**
 * The admin class is to maintain and manage data of the customers and the shop.
 */
public class Admin extends Person {

    /**
     * Constructor that creates an Admin object by invoking the abstract Person class through constructor chaining leveraging polymorphism.
     * @param id Identification of the admin.
     * @param firstName First name of the admin.
     * @param lastName Last name of the admin.
     * @param username Username of the admin.
     * @param password Password of the admin.
     */
    public Admin(int id, String firstName, String lastName, String username, String password) {
        super(id, firstName, lastName, username, password); // Leverage polymorphism.
    }

}