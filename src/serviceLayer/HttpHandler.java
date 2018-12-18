package serviceLayer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.Exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    public JsonNode request(String address) throws IOException,
            Exceptions.BadRequestException,
            Exceptions.NotFoundLocation,
            Exceptions.ConnectionError {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Content-Type","application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        int responseCode = connection.getResponseCode();
        String inputLine;
        while((inputLine = in.readLine())!=null){
            response.append(inputLine);
        }
        in.close();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.toString());
        if (responseCode == 400) {
            throw new Exceptions.BadRequestException("Given locations was not valid.", Exceptions.BadRequestException);
        } else if (responseCode == 200) {
            return jsonResponse;
        } else {
            throw new Exceptions.ConnectionError("Connection failed.", Exceptions.ConnectionError);
        }
    }
}
