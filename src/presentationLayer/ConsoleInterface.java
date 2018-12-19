package presentationLayer;

import businessLayer.CalculateWeather;

import java.io.IOException;

public class ConsoleInterface {
    private GenericIOFunctions genericIOFunctions;

    public ConsoleInterface() {
        this.genericIOFunctions = new GenericIOFunctions();
    }

    public void startInterface(CalculateWeather calculateWeather) throws IOException {
        System.out.println("Enter quit anywhere you'd like if you want to exit.");
        while (true) {
            String origin = genericIOFunctions.stringInputHandler("Where are you: ");
            String destination = genericIOFunctions.stringInputHandler("Where you'd like to go: ");
            String travelMode = genericIOFunctions.stringInputHandler("How will you travel(walking, driving): ");
            calculateWeather.calculateWeather(origin, destination, travelMode);
        }
    }
}
