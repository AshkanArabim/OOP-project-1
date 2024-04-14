package UI;

import datautils.CarCSVHandler;
import datautils.UserCSVHandler;
import entity.Person;

public abstract class UI {

    protected Person person;
    protected static final CarCSVHandler CARDATA = CarCSVHandler.getInstance();
    protected static final UserCSVHandler USERDATA  = UserCSVHandler.getInstance();

    public UI(Person person) {
        this.person = person;
    }
    
}
