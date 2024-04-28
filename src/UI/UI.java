package UI;
import datautils.CarCSVHandler;
import datautils.UserCSVHandler;
import entity.Person;

/**
 * Abstract class that makes car data and userdata for user interface purposes.
 */
public abstract class UI {
    
    /**
     * The person logged in.
     */
    protected Person person;

    /**
     * Singleton instance of CarCSVHandler to change the car csv.
     */
    protected static final CarCSVHandler CARDATA = CarCSVHandler.getInstance();

    /**
     * Singleton instance of UserCSVHandler to change the user csv.
     */
    protected static final UserCSVHandler USERDATA  = UserCSVHandler.getInstance();

    /**
     * Constructor that initializes person.
     * @param person The person logging into the system.
     */
    public UI(Person person) {
        this.person = person;
    }

    /**
     * Provides the main loop for users to see and perform different tasks.
     */
    protected abstract void menuLoop();
    
}
