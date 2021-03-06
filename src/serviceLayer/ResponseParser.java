package serviceLayer;

import businessLayer.Location;
import businessLayer.Route;
import businessLayer.WeatherCondition;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ResponseParser {
    public void mapsResponseParse(JsonNode response, List<Route> stepList) throws IOException {
        JsonNode routes = response.get("routes").get(0);
        JsonNode legs = routes.get("legs").get(0);
        JsonNode steps = legs.get("steps");
        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode step : steps) {
            Route route = new Route(
                    mapper.readValue(step.get("end_location").toString(), Location.class),
                    mapper.readValue(step.at("/distance/text").toString(), String.class),
                    mapper.readValue(step.at("/duration/text").toString(), String.class),
                    mapper.readValue(step.get("html_instructions").toString(), String.class)
            );
            stepList.add(route);
        }
    }

    public WeatherCondition weatherResponseParser(JsonNode response) {
        String summary = response.at("/currently/summary").toString();
        String temperature = response.at("/currently/temperature").toString();
        return new WeatherCondition(summary, Double.parseDouble(temperature));
    }
}
