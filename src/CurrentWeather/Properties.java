package CurrentWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private Temperature temperature;
    private Dewpoint dewpoint;
    private WindChill windChill;
    private RelativeHumidity relativeHumidity;

    public Temperature getTemperature() {
        return temperature;
    }

    public Dewpoint getDewpoint() {
        return dewpoint;
    }

    public WindChill getWindChill() {
        return windChill;
    }

    public RelativeHumidity getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void setDewpoint(Dewpoint dewpoint) {
        this.dewpoint = dewpoint;
    }

    public void setWindChill(WindChill windChill) {
        this.windChill = windChill;
    }

    public void setRelativeHumidity(RelativeHumidity relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }
}

