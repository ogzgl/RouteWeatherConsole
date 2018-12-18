package serviceLayer;

import Exceptions.Exceptions;
import businessLayer.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapsService {
    private String apiKey;
    private String mapsUrl;
    private HttpHandler httpHandler;

    public MapsService() {
        httpHandler = new HttpHandler();
        this.apiKey = "AIzaSyB-dtbrS9Xpb1gs2_2lHLKePFSE2e5WzKo";
        this.mapsUrl = "https://maps.googleapis.com/maps/api/directions/json?";
    }

    public List<Location> sendRequest(String origin, String destination, String travelMode) throws IOException {
        String requestUrl = mapsUrl + "origin=" + origin
                + "&destination=" + destination
                + "&mode=" + travelMode
                + "&key=" + apiKey;
        System.out.println(requestUrl);
        JsonNode response;
        try {
            response = httpHandler.request(requestUrl);
//            JsonNode steps = response.path("routes/0/legs/0/steps");
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
        } catch (Exceptions.NotFoundLocation | Exceptions.BadRequestException | Exceptions.ConnectionError e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}