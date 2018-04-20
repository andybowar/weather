package WeatherStation.CoordinatesEndpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private String observationStations;

    public String getObservationStations() {
        return observationStations;
    }

    public void setObservationStations(String observationStations) {
        this.observationStations = observationStations;
    }
}
