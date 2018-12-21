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
        /*
        * The stepList that is passed as argument to the mapsService is the field of CalculateWeatherClass.
        * Basically, it holds the direction steps that is returned from Google Maps for routing.
        * An example step is:
        * {
            "distance": {
            "text": "30 m",
            "value": 30
            },
          "duration": {
          "text": "1 min",
          "value": 5
          },
          "end_location": {
          "lat": 38.3194524,
          "lng": 26.6430566
          },
          "html_instructions": "Head <b>north</b> toward <b>İzmir YTE</b>",
          "polyline": {
          "points": "}e{hFwuraDs@K"
          },
          "start_location": {
          "lat": 38.3191909,
          "lng": 26.642999
          },
          "travel_mode": "DRIVING"
          }
        *
        *
        * The stepList comes in Json format from Google Maps API, this connection handled via Http request that is done
        * to Google Servers, parsed and mapped via Jackson(Json library).
        *
        * After parsing and mapping the Json, the stepList passed as argument to weather service,
        * the endpoint of each step is sent to DarkSky API as latitude, longitude information. The response of DarkSky
        * and Google Maps is combined and printed on presentation layer.
        * */
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
