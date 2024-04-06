package entity;

/**
 * Abstract class that leverages polymorphism by enabling the RunShop login to be determined based on the subtypes, Admin and User.
 */
public abstract class Person {
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
     * Email address of the person.
     */
    private String emailAddress;

    // getter & setters
    /************************************************************************/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdNumber() {
        return id;
    }

    public void setIdNumber(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /************************************************************************/
}