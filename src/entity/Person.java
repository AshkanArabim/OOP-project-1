package entity;

/**
 * Person class.
 */
public abstract class Person {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private int id;

    // private String dateOfBirth;
    
    private String emailAddress;

    // constructor

    public Person (
        int id, String firstName, String lastName, String username, String password
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // getter & setters

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

    // public String getDateOfBirth() {
    //     return dateOfBirth;
    // }

    // public void setDateOfBirth(String dateOfBirth) {
    //     this.dateOfBirth = dateOfBirth;
    // }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // methods
    
    // /**
    //  * Logs the person in.
    //  */
    // public abstract void login();
    
}