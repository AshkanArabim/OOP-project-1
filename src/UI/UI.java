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
    
}
