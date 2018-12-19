package businessLayer;

/*
 * This class holds the weather data that is obtained
 * via DarkSky Api.
 * */
public class WeatherCondition {
    private String summary; //summary holds the verbal explanation of weather e.g rainy
    private double temperature; // temperature in unit (C of F) decided auto. for location

    public WeatherCondition(String summary, double temperature) {
        setSummary(summary);
        setTemperature(temperature);
    }

    public double getTemperature() {
        return temperature;
    }

    public String getSummary() {
        return summary;
    }

    private void setSummary(String summary) {
        this.summary = summary;
    }

    private void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather will be " + summary.replace("\"", "") + " with temperature " + temperature + " \u00b0C";
    }
}
