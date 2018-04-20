package WeatherStation.StationsEndpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

    private Double coordinates;

    public Coordinates(){
        super();
    }

    public Coordinates(Double coordinates) {
        this.coordinates = coordinates;
    }

    public Double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double coordinates) {
        this.coordinates = coordinates;
    }
}
