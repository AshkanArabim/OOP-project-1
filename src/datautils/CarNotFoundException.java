package datautils;

/**
 * Exception thrown when admins or users attempt to work with cars that do not exist.
 */
public class CarNotFoundException extends Exception {
 
    /**
     * Will get thrown if a user or admin attempts to access a nonexistant car.
     * @param id The ID of the invalid car.
     */
    public CarNotFoundException(int id) {
        super("Invalid Car! " + id);
    }

}