import businessLayer.Location;
import businessLayer.WeatherCondition;
import serviceLayer.MapsService;
import serviceLayer.WeatherService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        MapsService ms = new MapsService();
        WeatherService ws = new WeatherService();
        List<Location> route = ms.sendRequest("izmir,turkey", "ankara,turkey", "driving");
        List<WeatherCondition> wc = ws.retrieveWeatherInformation(route);
        for (WeatherCondition w : wc) {
            System.out.println(w.toString());
        }
    }
}
