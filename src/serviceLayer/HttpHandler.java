package serviceLayer;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    public JSONObject request(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Content-Type","application/json");
        int responseCode = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while((inputLine = in.readLine())!=null){
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());
    }
}
