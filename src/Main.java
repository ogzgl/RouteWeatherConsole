import businessLayer.CalculateWeather;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CalculateWeather cw = new CalculateWeather();
        cw.calculateWeather("konak,izmir", "urla,izmir", "driving");
    }
}
