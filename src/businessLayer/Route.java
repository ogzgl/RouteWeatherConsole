package businessLayer;

/*
 * This class holds the every step information in Json that
 * is returned via Google Maps API.
 * */
public class Route {
    private Location endLocation; //endLocation is the finish point of the step.
    private String totalDistance; // distance required to take step
    private String duration; // time required to take step
    private String instruction; //route instruction(For instance take right turn.)
    private WeatherCondition weatherCondition;

    public Route(Location endLocation, String distance, String duration, String instruction) {
        setEndLocation(endLocation);
        setTotalDistance(distance);
        setDuration(duration);
        setInstruction(instruction);
    }

    private void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    private void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    private void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    private void setDuration(String duration) {
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
