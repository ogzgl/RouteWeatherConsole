package serviceLayer;

import businessLayer.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResponseParser {
    public List<Location> mapsResponseParse(JsonNode response) throws IOException {
        JsonNode routes = response.get("routes").get(0);
        JsonNode legs = routes.get("legs").get(0);
        JsonNode steps = legs.get("steps");
        Iterator<JsonNode> stepsIterator = steps.elements();
        List<Location> allSteps = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode step : steps) {
            Location loc = mapper.readValue(step.get("start_location").toString(), Location.class);
            allSteps.add(loc);
        }
        return allSteps;
    }
}
