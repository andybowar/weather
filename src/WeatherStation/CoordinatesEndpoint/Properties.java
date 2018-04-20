package WeatherStation.CoordinatesEndpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private String observationStations;
    private String forecastZone;

    public String getObservationStations() {
        return observationStations;
    }

    public void setObservationStations(String observationStations) {
        this.observationStations = observationStations;
    }

    public String getForecastZone() {
        return forecastZone;
    }

    public void setForecastZone(String forecastZone) {
        this.forecastZone = forecastZone;
    }
}
