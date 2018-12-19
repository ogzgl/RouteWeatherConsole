import businessLayer.CalculateWeather;
import presentationLayer.ConsoleInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CalculateWeather cw = new CalculateWeather();
        ConsoleInterface ci = new ConsoleInterface();
        ci.startInterface(cw);
    }
}