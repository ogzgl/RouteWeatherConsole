package serviceLayer;

import businessLayer.Location;
import businessLayer.WeatherCondition;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.Exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {
    private String darkskyKey;
    private String darkskyUrl;
    private HttpHandler httpHandler;

    public WeatherService() {
        httpHandler = new HttpHandler();
        this.darkskyKey = "434667c5ff1e9c07bc0b5185334bee5c";
        this.darkskyUrl = "https://api.darksky.net/forecast/";
    }

    public List<WeatherCondition> retrieveWeatherInformation(List<Location> locationList) throws IOException {
        String requestUrl;
        List<WeatherCondition> weatherConditionList = new ArrayList<>();
        for (Location loc : locationList) {
            requestUrl = darkskyUrl
                    + darkskyKey + "/"
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
}
