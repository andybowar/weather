package main.java.WeatherStation;

import main.java.WeatherStation.CoordinatesEndpoint.CoordinatesEndpoint;
import main.java.WeatherStation.StationsEndpoint.Features;
import main.java.WeatherStation.StationsEndpoint.StationsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FindWeatherStation {

    private String forecastUrl;
    private String stationUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        // Get endpoint for the list of observation stations near the given coordinates
        String latLon = "44.9038,-93.1782";
        CoordinatesEndpoint coordinatesEndpoint = restTemplate
                .getForObject("https://api.weather.gov/points/"
                        + latLon, CoordinatesEndpoint.class);

        // observationStations variable now contains URL for the endpoint needed to find the list of observation stations
        String observationStations = coordinatesEndpoint.getProperties().getObservationStations();
        forecastUrl = coordinatesEndpoint.getProperties().getForecast();

        // Stats of each weather station are stored in the 'features' list
        StationsEndpoint stationsEndpoint = restTemplate.getForObject(observationStations, StationsEndpoint.class);
        List<Features> features = stationsEndpoint.getFeatures();
        stationUrl = features.get(0).getId();
    }

    String getStationUrl() {
        return stationUrl;
    }

    String getForecastUrl() {
        return forecastUrl;
    }

}
