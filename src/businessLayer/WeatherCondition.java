package businessLayer;

public class WeatherCondition {
    private String summary;
    private double temperature;

    public WeatherCondition(String summary, double temperature) {
        setSummary(summary);
        setTemperature(temperature);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather will be " + summary + " with temperature " + temperature + " \u00b0C";
    }
}
