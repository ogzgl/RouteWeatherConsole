package businessLayer;

import serviceLayer.MapsService;
import serviceLayer.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculateWeather {
    private MapsService mapsService;
    private WeatherService weatherService;
    private List<Route> stepList;

    public CalculateWeather() {
        this.stepList = new ArrayList<>();
        this.mapsService = new MapsService();
        this.weatherService = new WeatherService();
    }

    public void calculateWeather(String origin, String destination, String travelMode) throws IOException {
        mapsService.routeRequest(origin, destination, travelMode, stepList);
        weatherService.retrieveWeatherInformation(stepList);
        if (stepList.size() == 0) {
            System.out.println("Nothing to show.");
        } else {
            for (Route step : stepList) {
                System.out.println(step.toString());
            }
        }
    }
}
