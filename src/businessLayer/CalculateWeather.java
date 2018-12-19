package businessLayer;

import serviceLayer.MapsService;
import serviceLayer.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Main class for program to run.
 * Handles the MapsService and WeatherService and coordination between them.
 * For usage of this class, an instance of this passed as argument to console interface.
 * */
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
        if (stepList.size() == 0) { // condition check if Google Maps returned 0 value.
            System.out.println("Nothing to show.");
        } else {
            for (Route step : stepList) {
                System.out.println(step.toString());
            }
        }
    }
}
