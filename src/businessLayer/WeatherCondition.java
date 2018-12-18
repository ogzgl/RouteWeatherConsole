package businessLayer;

public class WeatherCondition {
    private String summary;
    private double temperature;

    public WeatherCondition(String summary, double temperature) {
        setSummary(summary);
        setTemperature(temperature);
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
