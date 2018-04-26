package WeatherStation.StationsEndpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private String type;
    private Double[] coordinates;

    public String getType() {
        return type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
