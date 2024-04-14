package UI;

import datautils.CarCSVHandler;
import datautils.UserCSVHandler;
import entity.User;
import vehicles.Car;

public abstract class UI {

    protected User user;
    protected static final CarCSVHandler CARDATA = CarCSVHandler.getInstance();
    protected static final UserCSVHandler USERDATA  = UserCSVHandler.getInstance();

    public UI(User user) {
        this.user = user;
    }

    // /**
    //  * Displays all cars from the CSV file.
    //  */
    // // FIXME: move this to the parent UI class
    // private static void displayAllCars() {
    //     for (Car car : cars) {
    //         System.out.println(car);
    //     }

    //     System.out.println("");
    //     System.out.println("Row content:");
    //     System.out.println("[ID \t Type \t Mode \t Condition \t Color \t Capacity \t Mileage \t Fuel Type \t Transmission Type \t VIN \t Price \t Cars Available]");
    // }
    
}
