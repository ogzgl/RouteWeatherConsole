package serviceLayer;

import businessLayer.Location;
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
    private Properties properties;

    public MapsService() {
        this.mapsUrl = "https://maps.googleapis.com/maps/api/directions/json?";
        httpHandler = new HttpHandler();
        responseParser = new ResponseParser();
    }

    public List<Location> sendRequest(String origin, String destination, String travelMode) throws IOException {
        String requestUrl = mapsUrl + "origin=" + origin
                + "&destination=" + destination
                + "&mode=" + travelMode
                + "&key=" + propertyReader();
        try {
            response = httpHandler.request(requestUrl);
            return responseParser.mapsResponseParse(response);
        } catch (Exceptions.NotFoundLocation | Exceptions.BadRequestException | Exceptions.ConnectionError e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String propertyReader() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/app.conf"));
        return properties.get("MAPS_API_KEY").toString();
    }
}