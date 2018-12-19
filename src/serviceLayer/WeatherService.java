package serviceLayer;

import businessLayer.Route;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.Exceptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class WeatherService {
    private String darkskyUrl;
    private HttpHandler httpHandler;
    private ResponseParser responseParser;

    public WeatherService() {
        this.darkskyUrl = "https://api.darksky.net/forecast/";
        httpHandler = new HttpHandler();
        responseParser = new ResponseParser();
    }

    public void retrieveWeatherInformation(List<Route> stepList) throws IOException {
        String requestUrl;
        if (stepList.size() == 0) return;
        for (Route route : stepList) {
            requestUrl = darkskyUrl
                    + propertyReader() + "/"
                    + route.getEndLocation().getLat() + ","
                    + route.getEndLocation().getLng()
                    + "?exclude=minutely,hourly,daily,alerts,flags&units=auto";
            try {
                JsonNode weatherInfoResponse = httpHandler.request(requestUrl);
                route.setWeatherCondition(responseParser.weatherResponseParser(weatherInfoResponse));
            } catch (Exceptions.BadRequestException | Exceptions.NotFoundLocation | Exceptions.ConnectionError e) {
                e.getMessage();
            }
        }
    }

    private String propertyReader() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/app.conf"));
        return properties.get("DARKSKY_API_KEY").toString();
    }
}
