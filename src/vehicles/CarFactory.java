package vehicles;
import java.util.HashMap;

public class CarFactory {
    private static String[] headers;
    private static HashMap<String,String> columnMap = new HashMap<>();
    public static Car createCar(String[] values) {
        if (values.length != headers.length) {
            for (String str : values) {
                System.out.println(str);
            }
        }
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

    public static void setHeaders(String[] headersIn) {
        headers = headersIn;
    }
}
 