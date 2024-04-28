package entity;

/**
 * Abstract class that leverages polymorphism by enabling the RunShop login to be determined based on the subtypes, Admin and User.
 */
public abstract class Person {

    /**
     * Username of the person.
     */
    private String username;

    /**
     * Password of the person.
     */
    private String password;

    /**
     * First name of the person.
     */
    private String firstName;

    /**
     * Last name of the person.
     */
    private String lastName;

    /**
     * ID of the person.
     */
    private int id;

    /**
     * Get the username of the person.
     * @return The username of the person.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the person.
     * @param username The username of the person.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the person.
     * @return The password of the person.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the person.
     * @param password The password of the person.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the first name of the person.
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the person.
     * @param firstName The first name of the person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the person.
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the person.
     * @param lastName The last name of the person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the ID number of the person.
     * @return The ID number of the person.
     */
    public int getIdNumber() {
        return id;
    }

    /**
     * Set the ID number of the person.
     * @param id The ID number of the person.
     */
    public void setIdNumber(int id) {
        this.id = id;
    }

    /**
     * Constructs a Person Object giving them a first/last name, and a username/password.
     * @param id Identification of the person.
     * @param firstName First name of the person.
     * @param lastName Last name of the Person.
     * @param username Username of the Person.
     * @param password Password of the Person.
     */    
    public Person (int id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

}