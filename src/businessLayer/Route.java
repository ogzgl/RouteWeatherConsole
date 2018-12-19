package businessLayer;

public class Route {
    private Location endLocation;
    private String totalDistance;
    private String duration;
    private String instruction;
    private WeatherCondition weatherCondition;

    public Route(Location endLocation, String distance, String duration, String instruction) {
        setEndLocation(endLocation);
        setTotalDistance(distance);
        setDuration(duration);
        setInstruction(instruction);
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "> " + instruction.replaceAll("<[^>]*>", "") + "\n" +
                "    Step duration:" + duration + "\n" +
                "    Step distance:" + totalDistance + "\n" +
                "    Step weather: " + weatherCondition.getSummary() + "\n" +
                "    with temperature " + weatherCondition.getTemperature() + "\u00b0C";
    }
}
