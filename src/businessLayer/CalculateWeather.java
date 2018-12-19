package businessLayer;

import serviceLayer.MapsService;
import serviceLayer.WeatherService;

import java.io.IOException;
import java.util.List;

public class CalculateWeather {
    private MapsService mapsService;
    private WeatherService weatherService;

    public CalculateWeather() {
        this.mapsService = new MapsService();
        this.weatherService = new WeatherService();
    }

    public void calculateWeather(String origin, String destination, String travelMode) throws IOException {
        List<Route> stepList = mapsService.routeRequest(origin, destination, travelMode);
        weatherService.retrieveWeatherInformation(stepList);
        for (Route step : stepList) {
            System.out.println(step.toString());
        }
    }
}
