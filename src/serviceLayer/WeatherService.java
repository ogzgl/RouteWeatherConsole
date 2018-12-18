package serviceLayer;

import businessLayer.Location;
import businessLayer.WeatherCondition;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.Exceptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WeatherService {
    private String darkskyUrl;
    private HttpHandler httpHandler;

    public WeatherService() {
        httpHandler = new HttpHandler();
        this.darkskyUrl = "https://api.darksky.net/forecast/";
    }

    public List<WeatherCondition> retrieveWeatherInformation(List<Location> locationList) throws IOException {
        String requestUrl;
        List<WeatherCondition> weatherConditionList = new ArrayList<>();
        for (Location loc : locationList) {
            requestUrl = darkskyUrl
                    + propertyReader() + "/"
                    + loc.getLat() + ","
                    + loc.getLng()
                    + "?exclude=minutely,hourly,daily,alerts,flags&units=auto";
            try {
                JsonNode weatherInfoResponse = httpHandler.request(requestUrl);
                String summary = weatherInfoResponse.at("/currently/summary").toString();
                String temperature = weatherInfoResponse.at("/currently/temperature").toString();
                weatherConditionList.add(new WeatherCondition(summary, Double.parseDouble(temperature)));
            } catch (Exceptions.BadRequestException | Exceptions.NotFoundLocation | Exceptions.ConnectionError e) {
                e.getMessage();
            }
        }
        return weatherConditionList;
    }

    private String propertyReader() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/app.conf"));
        return properties.get("DARKSKY_API_KEY").toString();
    }
}
