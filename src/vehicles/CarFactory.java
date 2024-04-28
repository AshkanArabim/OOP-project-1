package vehicles;
import java.util.HashMap;

/**
 * Design pattern responsible for returning a subtype (SUV,Sedan,Hatchback,Pickup) of Car.
 */
public class CarFactory {

    /**
     * The tokens of the top line in the car_data csv file.
     */
    private static String[] headers;

    /**
     * A hashmap that maps columns to attributes.
     */
    private static HashMap<String,String> columnMap = new HashMap<>();

    /**
     * A hashmap that maps columns to indices.
     */
    public static HashMap<String, Integer> columnMapIndices = new HashMap<>();

    /**
     * Creates a car based on the values of the CSV but responds dynamically to column changes.
     * @param values The top row in the CSV.
     * @return Instance of subtype of car based on the values from the row.
     */
    public static Car createCar(String[] values) {

        for (int i = 0; i < headers.length; i++) {
            columnMap.put(headers[i], values[i]);
        }

        String carType = columnMap.get("Car Type");
        
        switch(carType) {
            case("SUV"): return new SUV(columnMap);
            case("Hatchback"): return new Hatchback(columnMap);
            case("Pickup"): return new Pickup(columnMap);
            case("Sedan"): return new Sedan(columnMap);
        }

        return null;
    }

    /**
     * Sets the headers for the columns.
     * @param headersIn The columns read directly from the CSV file.
     */
    public static void setHeaders(String[] headersIn) {

        headers = headersIn;

        for (int i = 0; i < headers.length; i++) {
            columnMapIndices.put(headers[i], i);
        }

    }

    /**
     * Returns the headers attribute.
     * @return The headers attribute.
     */
    public static String[] getHeaders() {
        return headers;
    }
    
}