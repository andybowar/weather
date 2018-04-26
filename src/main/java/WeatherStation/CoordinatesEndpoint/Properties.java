package WeatherStation.CoordinatesEndpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private String observationStations;
    private String forecast;

    public String getObservationStations() {
        return observationStations;
    }

    public void setObservationStations(String observationStations) {
        this.observationStations = observationStations;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
