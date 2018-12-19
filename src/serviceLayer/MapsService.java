package serviceLayer;

import businessLayer.Route;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.Exceptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MapsService {
    private String mapsUrl;
    private HttpHandler httpHandler;
    private JsonNode response;
    private ResponseParser responseParser;

    public MapsService() {
        this.mapsUrl = "https://maps.googleapis.com/maps/api/directions/json?";
        httpHandler = new HttpHandler();
        responseParser = new ResponseParser();
    }

    public void routeRequest(String origin, String destination, String travelMode, List<Route> stepList) throws IOException {
        String requestUrl = mapsUrl + "origin=" + origin
                + "&destination=" + destination
                + "&mode=" + travelMode
                + "&key=" + propertyReader();
        try {
            response = httpHandler.request(requestUrl);
            responseParser.mapsResponseParse(response, stepList);
        } catch (Exceptions.BadRequestException | Exceptions.NotFoundLocation | Exceptions.ConnectionError e) {
            System.out.println(e.getMessage());
        }
    }

    private String propertyReader() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/app.conf"));
        return properties.get("MAPS_API_KEY").toString();
    }
}